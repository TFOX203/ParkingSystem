package com.parking.hilos;

import com.parking.modelo.Aparcamiento;

public class HiloEntrada implements Runnable {
    private Aparcamiento aparcamiento;

    public HiloEntrada(Aparcamiento aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    @Override
    public void run() {
        aparcamiento.entrarCoche();
    }
}