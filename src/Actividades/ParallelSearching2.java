package Actividades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearching2 {

	public static void main(String[] args) throws InterruptedException {

		Random random = new Random();

		// 1. Creamos el array con números aleatorios (ahora grande)
		int[] array = new int[10_000_000];

		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(1_000_000);
		}

		// Número que queremos buscar
		int num = 500_000;

		// Variable compartida
		AtomicBoolean encontrado = new AtomicBoolean(false);

		// 2. Hardware: nº de núcleos disponibles
		int procesadores = Runtime.getRuntime().availableProcessors();
		System.out.println("Ejecutando búsqueda paralela en " + procesadores + " núcleos...");

		// Nunca más hilos que elementos
		int numHilos = Math.max(1, Math.min(procesadores, array.length));

		// Tamaño de cada bloque
		int chunk = array.length / numHilos;

		// 3. Creamos y arrancamos los hilos en un bucle
		List<Thread> hilos = new ArrayList<>();

		for (int i = 0; i < numHilos; i++) {
			final int inicio = i * chunk;
			// El último hilo se queda con el resto de la división
			final int fin = (i == numHilos - 1) ? array.length : inicio + chunk;

			Thread hilo = new Thread(new Runnable() {

				@Override
				public void run() {
					buscarNumero(array, num, inicio, fin, encontrado);
				}
			});

			hilos.add(hilo);
			hilo.start();
		}

		// 4. Esperamos a que terminen todos
		for (Thread hilo : hilos) {
			hilo.join();
		}

		// Comprobamos si se ha encontrado
		if (!encontrado.get()) {
			System.out.println("El número no se ha encontrado");
		}
	}

	// Método que realiza la búsqueda (sin cambios)
	public static void buscarNumero(int[] array, int num, int inicio, int fin, AtomicBoolean encontrado) {

		for (int i = inicio; i < fin && !encontrado.get(); i++) {

			if (array[i] == num) {

				// Solo un hilo puede anunciar el hallazgo
				if (encontrado.compareAndSet(false, true)) {
					System.out.println("Número " + num + " encontrado en la posición " + i);
				}

				return;
			}
		}
	}
}