/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciosprocessbuilder;

import java.io.IOException;

/**
 *
 * @author gliatem
 */
public class AbrirUnaPaguinaNavegador {
    public static void main(String[] args) {
        try {
            System.out.println("Arrancando aplicacion");
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start https://www.google.com");
            pb.start();
            System.out.println("Listo");
            for (int i = 0; i < 10; i++) {
                pb = new ProcessBuilder("cmd", "/c", "start https://www.google.com");
                pb.start();
                System.out.println("Abriendo navegador " + i);
            }
        } catch(IOException e) {
            System.out.println("Error: " +  e.getMessage());
        }
    }
}
