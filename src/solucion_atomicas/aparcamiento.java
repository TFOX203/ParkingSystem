package solucion_atomicas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Solución 3: variable atómica (AtomicInteger) + CAS (compareAndSet), sin bloqueos.
 * Si otro hilo cambia el valor entre la lectura y la escritura, el CAS falla y se reintenta.
 *
 * @author Valenciano
 */
public class aparcamiento {
    private final String ciudad;
    private final String nombre;
    private final int aforoMaximo;
    private final AtomicInteger cochesAparcados = new AtomicInteger(0);

    public aparcamiento(String ciudad, String nombre, int aforoMaximo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.aforoMaximo = aforoMaximo;
    }

    public void entrarCoche() {
        int actual;
        do {
            actual = cochesAparcados.get();
            if (actual >= aforoMaximo) {
                return; // lleno: coche rechazado
            }
        } while (!cochesAparcados.compareAndSet(actual, actual + 1));
    }

    public void salirCoche() {
        int actual;
        do {
            actual = cochesAparcados.get();
            if (actual <= 0) {
                return; // vacío
            }
        } while (!cochesAparcados.compareAndSet(actual, actual - 1));
    }

    public int getCochesAparcados() {
        return cochesAparcados.get();
    }

    /** Libera recursos. Solo hace algo en la solución por mensajería. */
    public void cerrar() {
        // nada que liberar
    }

    public String getCiudad() { return ciudad; }
    public String getNombre() { return nombre; }
    public int getAforoMaximo() { return aforoMaximo; }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ") - " + getCochesAparcados() + "/" + aforoMaximo + " plazas";
    }
}
