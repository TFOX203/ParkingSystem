package com.parking.modelo;

public class Aparcamiento {
    private String ciudad;
    private String nombre;
    private int aforoMaximo;
    private int cochesAparcados;

    public Aparcamiento(String nombre, String ciudad, int aforoMaximo) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.aforoMaximo = aforoMaximo;
        this.cochesAparcados = 0;
    }

    public synchronized boolean entrarCoche() {
        if (cochesAparcados < aforoMaximo) {
            cochesAparcados++;
            System.out.println(Thread.currentThread().getName()
                + " -> ENTRA en " + nombre + " (" + cochesAparcados + "/" + aforoMaximo + ")");
            return true;
        } else {
            System.out.println(Thread.currentThread().getName()
                + " -> RECHAZADO, aparcamiento lleno en " + nombre);
            return false;
        }
    }

    public synchronized boolean salirCoche() {
        if (cochesAparcados > 0) {
            cochesAparcados--;
            System.out.println(Thread.currentThread().getName()
                + " -> SALE de " + nombre + " (" + cochesAparcados + "/" + aforoMaximo + ")");
            return true;
        } else {
            System.out.println(Thread.currentThread().getName()
                + " -> ERROR, no hay coches en " + nombre);
            return false;
        }
    }

    public synchronized int getCochesAparcados() {
        return cochesAparcados;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }
}