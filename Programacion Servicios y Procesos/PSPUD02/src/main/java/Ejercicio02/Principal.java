/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio02;

/**
 *
 * @author gliatem
 */
public class Principal {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Hilo h = new Hilo();
            h.start();
        }
        System.out.println("Hilo Terminado");
    }
    
}
