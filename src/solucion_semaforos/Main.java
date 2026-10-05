package solucion_semaforos;

/**
 * Demostración: 4 hilos x 500.000 entradas cada uno.
 *
 * @author Valenciano
 */
public class Main {

    static final int HILOS = 4;
    static final int ITERACIONES = 500_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== solucion_semaforos ===");

        // Escenario A: aforo enorme -> el contador final debe ser HILOS * ITERACIONES
        aparcamiento parkingA = new aparcamiento("madrid", "Aparcamiento 1", 100_000_000);
        long inicio = System.nanoTime();
        lanzar(parkingA);
        parkingA.cerrar();
        long ms = (System.nanoTime() - inicio) / 1_000_000;
        int esperadoA = HILOS * ITERACIONES;
        int obtenidoA = parkingA.getCochesAparcados();
        System.out.println("A) esperado=" + esperadoA + " obtenido=" + obtenidoA
                + " tiempo=" + ms + "ms -> " + (esperadoA == obtenidoA ? "OK" : "ERROR"));

        // Escenario B: aforo pequeño -> NUNCA puede haber más coches que plazas
        aparcamiento parkingB = new aparcamiento("barcelona", "Aparcamiento 2", 1000);
        lanzar(parkingB);
        parkingB.cerrar();
        int obtenidoB = parkingB.getCochesAparcados();
        System.out.println("B) aforo=1000 obtenido=" + obtenidoB + " -> " + (obtenidoB == 1000 ? "OK" : "ERROR"));
    }

    private static void lanzar(aparcamiento parking) throws InterruptedException {
        Thread[] hilos = new Thread[HILOS];
        for (int i = 0; i < HILOS; i++) {
            hilos[i] = new EntranceJob(parking, "Justo" + i, ITERACIONES);
        }
        for (Thread t : hilos) {
            t.start();          // todos arrancan a la vez
        }
        for (Thread t : hilos) {
            t.join();           // y después se espera a todos
        }
    }
}
