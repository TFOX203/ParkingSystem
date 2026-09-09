package com.parking.modelo;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private List<Aparcamiento> aparcamientos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.aparcamientos = new ArrayList<>();
    }

    public void registrarAparcamiento(Aparcamiento a) {
        aparcamientos.add(a);
    }

    public List<Aparcamiento> buscarPorCiudad(String ciudad) {
        List<Aparcamiento> resultado = new ArrayList<>();
        for (Aparcamiento a : aparcamientos) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public void consultaGeneral() {
        System.out.println("=== Estado de " + nombre + " ===");
        for (Aparcamiento a : aparcamientos) {
            System.out.println(a.getNombre() + " (" + a.getCiudad() + "): "
                + a.getCochesAparcados() + "/" + a.getAforoMaximo());
        }
    }

    public List<Aparcamiento> getAparcamientos() {
        return aparcamientos;
    }
}