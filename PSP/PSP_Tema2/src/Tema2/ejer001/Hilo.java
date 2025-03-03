/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.ejer001;

/**
 *
 * @author damiansualdea
 */
public class Hilo extends Thread{
    @Override
    public void run(){
        for(int i = 0; i<=10; i++){
            System.out.println("Soy el hilo: "+i);
        }
    }
}
