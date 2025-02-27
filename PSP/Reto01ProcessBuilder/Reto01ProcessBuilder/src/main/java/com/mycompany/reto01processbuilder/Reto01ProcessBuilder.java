/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.reto01processbuilder;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gliatem
 * Hola Damian te dejo por aqui la explicacion.
 * lo que echo a sido guiarme de lo que haciamos en el video y luego ir ampliando con lo que el 
 * netBeans me daba de pistas de los try catch.
 * 
 * 
 * Lo probado en mac y en windows te dejo las rutas aqui.
 * Ruta mac: /usr/bin/gnome-calculator
 * Ruta Windows: C:\Windows\System32\calc.exe


 */
public class Reto01ProcessBuilder {

    public static void main(String[] args) {
        // Paso 1: Solicitar la ruta del ejecutable
        try (Scanner teclado = new Scanner(System.in)) { // esto es lo que te decia me recomendaba netBeans ponerlo asi
            
            // Paso 1: Solicitar la ruta del ejecutable
            System.out.print("Introduce la ruta del ejecutable: ");
            String rutaEjecutable = teclado.nextLine();
            // Paso 2: Solicitar el tiempo de espera en segundos
            System.out.print("Introduce el tiempo en segundos: ");
            int tiempoEspera = teclado.nextInt();
            
            // Paso 3: Esperar el tiempo indicado
            System.out.println("Esperando " + tiempoEspera + " segundos...");
            
            try {
                Thread.sleep(tiempoEspera * 1000);
            } catch (InterruptedException e) {
                System.out.println("La espera fue interrumpida.");
            }
            // Paso 4: Lanzar el programa con ProcessBuilder aunque en si a sido lo primero que puesto
            try {
                ProcessBuilder proceso = new ProcessBuilder(rutaEjecutable);
                proceso.start();
                System.out.println("PROGRAMA LANZADO");
            }catch (IOException e) {
                System.out.println("Error al ejecutar el programa: " + e.getMessage());
            }
            teclado.close();
        }
    }
}
