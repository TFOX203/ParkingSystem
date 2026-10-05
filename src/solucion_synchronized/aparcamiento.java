package solucion_synchronized;

/**
 * Solución 1: monitor intrínseco con 'synchronized'.
 * Solo un hilo a la vez puede ejecutar los métodos sincronizados de ESTE objeto.
 *
 * @author Valenciano
 */
public class aparcamiento {
    private final String ciudad;
    private final String nombre;
    private final int aforoMaximo;
    private int cochesAparcados;

    public aparcamiento(String ciudad, String nombre, int aforoMaximo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.aforoMaximo = aforoMaximo;
        this.cochesAparcados = 0;
    }

    public void entrarCoche() {
        // sección crítica completa: comprobar + incrementar
        synchronized (this) {
            if (cochesAparcados < aforoMaximo) {
                cochesAparcados++;
            }
        }
    }

    public void salirCoche() {
        synchronized (this) {
            if (cochesAparcados > 0) {
                cochesAparcados--;
            }
        }
    }

    public int getCochesAparcados() {
        synchronized (this) {
            return cochesAparcados;
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
