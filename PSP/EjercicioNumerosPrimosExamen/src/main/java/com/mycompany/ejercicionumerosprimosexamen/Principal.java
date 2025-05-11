/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicionumerosprimosexamen;

/**
 *
 * @author gliatem
 */
public class Principal {
    public static void main(String[] args) {
        
        PrimoThread hilo1 = new PrimoThread(500);
        PrimoThread hilo2 = new PrimoThread(1000);
        PrimoThread hilo3 = new PrimoThread(2000);
        
        
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.out.println("Error en los hilos" + e);
        }
     
        int primo1 = hilo1.getResultado();
        int primo2 = hilo2.getResultado();
        int primo3 = hilo3.getResultado();
        
        double resultado = (primo1 + primo2 + primo3) / 3.0;
        
        
        System.out.println("Próximo primo después de 500: " + primo1);
        System.out.println("Próximo primo después de 1000: " + primo2);
        System.out.println("Próximo primo después de 2000: " + primo3);
        System.out.println("Resultado de la fórmula: " + resultado);
        
        
        
    }
}
