/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciosprocessbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author gliatem
 */
public class LIstarArchivosDirectorio {
    public static void main(String[] args) {
         try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir"); // Windows
            Process proceso = pb.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al listar archivos: " + e.getMessage());
        }
    }
}
