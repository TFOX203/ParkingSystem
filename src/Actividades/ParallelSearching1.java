
package Actividades;

import java.security.PublicKey;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import jdk.dynalink.beans.StaticClass;

public class ParallelSearching1 {

	public static void main(String[] args) throws InterruptedException {

		Random random = new Random();

		// 1. Creamos el array con números aleatorios
		int[] array = new int[1000];

		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(1000);
		}

		// Número que queremos buscar
		int num = 500;

		// Variables para compartir el resultado
		AtomicBoolean encontrado = new AtomicBoolean(false);
		int mitad = array.length / 2;

		// 2. Creamos el primer hilo
		Thread hilo1 = new Thread(new Runnable() {

			@Override
			public void run() {
				buscarNumero(array, num, 0, mitad, encontrado);
			}
		});

		// 3. Creamos el segundo hilo
		Thread hilo2 = new Thread(new Runnable() {

			@Override
			public void run() {
				buscarNumero(array, num, mitad, array.length, encontrado);
			}
		});

		// Iniciamos los dos hilos
		hilo1.start();
		hilo2.start();

		// Esperamos a que terminen los dos hilos
		hilo1.join();
		hilo2.join();

		// Comprobamos si se ha encontrado
		if (!encontrado.get()) {
			System.out.println("El número no se ha encontrado");
		}
	}

	// Método que realiza la búsqueda
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
