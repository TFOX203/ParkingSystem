package Actividades;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearching3 {

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

		// 3. Creamos un ExecutorService con un pool fijo de hilos
		ExecutorService executor = Executors.newFixedThreadPool(numHilos);

		for (int i = 0; i < numHilos; i++) {
			final int inicio = i * chunk;
			// El último hilo se queda con el resto de la división
			final int fin = (i == numHilos - 1) ? array.length : inicio + chunk;

			// Tarea definida con función flecha (lambda)
			executor.execute(() -> buscarNumero(array, num, inicio, fin, encontrado));
		}

		// 4. No se aceptan más tareas y esperamos a que terminen todas
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);

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