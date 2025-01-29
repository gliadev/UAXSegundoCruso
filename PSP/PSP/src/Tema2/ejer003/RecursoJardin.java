/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.ejer003;

/**
 *
 * @author damiansualdea
 */
public class RecursoJardin {

    private int cuenta;

    public RecursoJardin() {
        this.cuenta = 100;
    }

    public  void  incrementaCuenta() {
        System.out.println("Persona " + Thread.currentThread().getName() + "---- entra en el jardin");
        cuenta++;
        System.out.println(cuenta + " personas en el jardín");

    }
//synchronized
    public  void decrementaCuenta() {
        System.out.println("Persona " + Thread.currentThread().getName() + "---- sale en el jardin");
        cuenta--;
        System.out.println(cuenta + " personas en el jardín");

    }
}
