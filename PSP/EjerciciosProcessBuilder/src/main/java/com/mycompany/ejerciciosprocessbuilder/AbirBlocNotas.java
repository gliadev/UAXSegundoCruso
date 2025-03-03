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
public class AbirBlocNotas {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("notepad");
            pb.start();
            System.out.println("Abriendo bloc");
        } catch (IOException e) {
            System.out.println("Error al abrir el bloc de Notas" + e.getLocalizedMessage());
        }
    }
}
