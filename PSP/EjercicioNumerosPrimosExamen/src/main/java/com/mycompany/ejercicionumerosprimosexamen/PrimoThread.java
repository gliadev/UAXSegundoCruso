/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicionumerosprimosexamen;

/**
 *
 * @author gliatem
 */

public class PrimoThread extends Thread {
    private int numeroBase;
    private int resultado;

    public PrimoThread(int numeroBase) {
        this.numeroBase = numeroBase;
    }

    public int getResultado() {
        return resultado;
    }

    private boolean esPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private int proximoPrimo(int num) {
        int candidato = num + 1;
        while (!esPrimo(candidato)) {
            candidato++;
        }
        return candidato;
    }

    @Override
    public void run() {
        resultado = proximoPrimo(numeroBase);
    }
}

