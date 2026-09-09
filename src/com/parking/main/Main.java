package com.parking.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.parking.hilos.HiloEntrada;
import com.parking.hilos.HiloSalida;
import com.parking.modelo.Aparcamiento;
import com.parking.modelo.Empresa;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // 1. Crear la empresa y un aparcamiento
        Empresa empresa = new Empresa("ParkingSA");
        Aparcamiento madrid = new Aparcamiento("Sol", "Madrid", 5);
        empresa.registrarAparcamiento(madrid);

        // 2. Crear un pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 3. Lanzar 8 hilos de entrada (aforo es 5, así que 3 serán rechazados)
        for (int i = 0; i < 8; i++) {
            executor.submit(new HiloEntrada(madrid));
        }

        // 4. Lanzar 3 hilos de salida
        for (int i = 0; i < 3; i++) {
            executor.submit(new HiloSalida(madrid));
        }

        // 5. Cerrar el executor y esperar a que terminen todos los hilos
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // 6. Mostrar el estado final
        empresa.consultaGeneral();
    }
}
