/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.ejer001;

/**
 *
 * @author damiansualdea
 */
public class Principal {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Soy el programa principal");
        Hilo h1 = new Hilo();
        Hilo2 h2 = new Hilo2();

        h1.start();//Lanzamos el hilo 1 

        h2.start(); //Lanzamos el hilo 2 
        h2.join();
        h1.join();
        //va a esperar h1 y h2
        System.out.println("Programa terminado");

    }
}
