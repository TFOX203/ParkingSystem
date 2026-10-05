package solucion_locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Solución 2: bloqueo explícito con ReentrantLock (java.util.concurrent.locks).
 * Igual que synchronized pero con control manual (tryLock, fairness, interrupciones).
 *
 * @author Valenciano
 */
public class aparcamiento {
    private final String ciudad;
    private final String nombre;
    private final int aforoMaximo;
    private int cochesAparcados;
    private final ReentrantLock cerrojo = new ReentrantLock();

    public aparcamiento(String ciudad, String nombre, int aforoMaximo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.aforoMaximo = aforoMaximo;
        this.cochesAparcados = 0;
    }

    public void entrarCoche() {
        cerrojo.lock();
        try {
            if (cochesAparcados < aforoMaximo) {
                cochesAparcados++;
            }
        } finally {
            cerrojo.unlock(); // SIEMPRE en finally
        }
    }

    public void salirCoche() {
        cerrojo.lock();
        try {
            if (cochesAparcados > 0) {
                cochesAparcados--;
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public int getCochesAparcados() {
        cerrojo.lock();
        try {
            return cochesAparcados;
        } finally {
            cerrojo.unlock();
        }
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
