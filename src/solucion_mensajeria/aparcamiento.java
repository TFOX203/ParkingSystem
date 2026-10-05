package solucion_mensajeria;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Solución 5: paso de mensajes (modelo productor-consumidor / actor).
 * Los hilos NO tocan el contador: envían mensajes a una cola y UN ÚNICO hilo gestor los procesa.
 * El estado queda confinado a un solo hilo -> no hace falta sincronizar el contador.
 *
 * @author Valenciano
 */
public class aparcamiento {
    private final String ciudad;
    private final String nombre;
    private final int aforoMaximo;
    private enum Mensaje { ENTRADA, SALIDA, FIN }

    private final BlockingQueue<Mensaje> cola = new LinkedBlockingQueue<>();
    private final Thread gestor;
    private volatile int cochesAparcados; // solo lo escribe el hilo gestor

    public aparcamiento(String ciudad, String nombre, int aforoMaximo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.aforoMaximo = aforoMaximo;
        this.cochesAparcados = 0;
        this.gestor = new Thread(this::procesarMensajes, "gestor-" + nombre);
        this.gestor.setDaemon(true);
        this.gestor.start();
    }

    public void entrarCoche() {
        cola.add(Mensaje.ENTRADA);
    }

    public void salirCoche() {
        cola.add(Mensaje.SALIDA);
    }

    public int getCochesAparcados() {
        return cochesAparcados;
    }

    /** Libera recursos. Solo hace algo en la solución por mensajería. */
    public void cerrar() {
        cola.add(Mensaje.FIN);
        try {
            gestor.join(); // espera a que se procesen TODOS los mensajes pendientes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /** Bucle del hilo gestor: es el ÚNICO que modifica el contador. */
    private void procesarMensajes() {
        try {
            while (true) {
                Mensaje m = cola.take();
                if (m == Mensaje.FIN) {
                    break;
                }
                if (m == Mensaje.ENTRADA && cochesAparcados < aforoMaximo) {
                    cochesAparcados++;
                } else if (m == Mensaje.SALIDA && cochesAparcados > 0) {
                    cochesAparcados--;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getCiudad() { return ciudad; }
    public String getNombre() { return nombre; }
    public int getAforoMaximo() { return aforoMaximo; }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ") - " + getCochesAparcados() + "/" + aforoMaximo + " plazas";
    }
}
