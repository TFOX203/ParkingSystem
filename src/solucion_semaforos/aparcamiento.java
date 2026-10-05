package solucion_semaforos;

import java.util.concurrent.Semaphore;

/**
 * Solución 4: semáforos. Cada plaza libre es un 'permiso'.
 * plazasLibres + plazasOcupadas == aforoMaximo siempre; entrar mueve un permiso de un semáforo al otro.
 *
 * @author Valenciano
 */
public class aparcamiento {
    private final String ciudad;
    private final String nombre;
    private final int aforoMaximo;
    private final Semaphore plazasLibres;
    private final Semaphore plazasOcupadas = new Semaphore(0);

    public aparcamiento(String ciudad, String nombre, int aforoMaximo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.aforoMaximo = aforoMaximo;
        this.plazasLibres = new Semaphore(aforoMaximo);
    }

    public void entrarCoche() {
        if (plazasLibres.tryAcquire()) { // hay hueco
            plazasOcupadas.release();
        }
    }

    public void salirCoche() {
        if (plazasOcupadas.tryAcquire()) { // hay algún coche dentro
            plazasLibres.release();
        }
    }

    public int getCochesAparcados() {
        return plazasOcupadas.availablePermits();
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
