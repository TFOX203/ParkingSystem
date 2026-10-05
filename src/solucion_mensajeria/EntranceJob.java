package solucion_mensajeria;

/**
 * Hilo que simula la llegada de coches a un aparcamiento.
 *
 * @author Valenciano
 */
public class EntranceJob extends Thread {

    private final aparcamiento parking;
    private final int iteraciones;

    public EntranceJob(aparcamiento parking, String name, int iteraciones) {
        super(name);
        this.parking = parking;
        this.iteraciones = iteraciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < iteraciones; i++) {
            parking.entrarCoche();
        }
    }
}
