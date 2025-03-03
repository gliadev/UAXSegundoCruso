/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.ejer002;

/**
 *
 * @author damiansualdea
 */
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        ListaPaciente l1 = new ListaPaciente("l1",new int[]{3,5,7,1,2});
        ListaPaciente l2 = new ListaPaciente("l2",new int[]{4,6,1,2,3});
         long inicio = System.currentTimeMillis();
        Enfermero e1 = new Enfermero("Valentino",l1, inicio);
        Enfermero e2 = new Enfermero("Mark",l2,inicio);
        
        e1.start();
        e1.join();
        
        e2.start();
        e2.join();
        
    }
}
