
package Actividades;

import java.util.Random;

public class Practicas {

	public static void main(String[] args) {

		Random random = new Random();

		// 1. Creamos el array con números aleatorios
		int[] array = new int[1000];

		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(1000);
		}

		// Número que queremos buscar
		int num = 500;

		// 2. Creamos el hilo
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {

				// 3. El hilo busca el número
				System.out.println("Número seleccionado: " + num);

				System.out.println("Buscando en el array...");

				boolean encontrado = false;

				for (int i = 0; i < array.length; i++) {

					if (array[i] == num) {
						System.out.println("Se encontró el número " + num + " en la posición " + i);

						encontrado = true;
					}
				}

				if (!encontrado) {
					System.out.println("El número no se ha encontrado");
				}
			}
		});

		// Iniciamos el hilo
		hilo.start();
	}
}
