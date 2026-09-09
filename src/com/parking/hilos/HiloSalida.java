package com.parking.hilos;

import com.parking.modelo.Aparcamiento;

public class HiloSalida implements Runnable {
    private Aparcamiento aparcamiento;

    public HiloSalida(Aparcamiento aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    @Override
    public void run() {
        aparcamiento.salirCoche();
    }
}