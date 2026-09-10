package com.parking.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Empresa {
    private String nombre;
    private Map<String, Aparcamiento> aparcamientos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        // LinkedHashMap para mantener el orden de inserción al listar
        this.aparcamientos = new LinkedHashMap<>();
    }

    public void registrarAparcamiento(Aparcamiento a) {
        aparcamientos.put(a.getNombre(), a);
    }

    /**
     * Acceso directo O(1) por nombre de aparcamiento (clave del Map).
     */
    public Aparcamiento buscarPorNombre(String nombreAparcamiento) {
        return aparcamientos.get(nombreAparcamiento);
    }

    /**
     * La ciudad no es la clave del Map, así que aquí sí es necesario
     * recorrer los valores (no hay forma de indexar por ciudad sin
     * mantener un segundo índice). Sigue siendo más simple gracias a
     * values(), pero el coste continúa siendo O(n).
     */
    public List<Aparcamiento> buscarPorCiudad(String ciudad) {
        List<Aparcamiento> resultado = new ArrayList<>();
        for (Aparcamiento a : aparcamientos.values()) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public void consultaGeneral() {
        System.out.println("=== Estado de " + nombre + " ===");
        for (Aparcamiento a : aparcamientos.values()) {
            System.out.println(a.getNombre() + " (" + a.getCiudad() + "): "
                + a.getCochesAparcados() + "/" + a.getAforoMaximo());
        }
    }

    /**
     * Elimina un aparcamiento en O(1) por nombre.
     */
    public boolean eliminarAparcamiento(String nombreAparcamiento) {
        return aparcamientos.remove(nombreAparcamiento) != null;
    }

    /**
     * Comprueba existencia en O(1).
     */
    public boolean existeAparcamiento(String nombreAparcamiento) {
        return aparcamientos.containsKey(nombreAparcamiento);
    }

    public Collection<Aparcamiento> getAparcamientos() {
        return aparcamientos.values();
    }

    public Map<String, Aparcamiento> getMapaAparcamientos() {
        return aparcamientos;
    }
}