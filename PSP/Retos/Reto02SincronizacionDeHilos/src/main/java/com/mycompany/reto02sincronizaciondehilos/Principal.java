/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto02sincronizaciondehilos;

/**
 *
 * @author gliatem
 */
public class Principal {
    public static void main(String[] args) {
        
         // Creamos tres hilos para calcular la suma de impares de diferentes números
        Sumatorio s1 = new Sumatorio(20);
        Sumatorio s2 = new Sumatorio(23);
        Sumatorio s3 = new Sumatorio(12);

        // Iniciamos los hilos (cada uno empieza a ejecutar su run())
        s1.start();
        s2.start();
        s3.start();

        // Hacemos que el hilo principal espere a que terminen los cálculos
        // Esto es necesario para asegurar que los resultados están listos antes de usarlos.
        try {
            s1.join();
            s2.join();
            s3.join();
        } catch (InterruptedException e) {
            System.out.println("Error en la espera de los hilos");
        }

        // Sumamos los resultados de cada hilo
        double suma = s1.getResultado() + s2.getResultado() + s3.getResultado();
        // Aplicamos la fórmula: dividimos la suma entre 8
        double formula = suma / 8;

        // Mostramos el resultado final en consola
        System.out.println("El resultado es: " + formula);
    }
}
