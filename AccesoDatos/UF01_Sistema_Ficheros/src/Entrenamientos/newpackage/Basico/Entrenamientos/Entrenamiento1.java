/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entrenamientos.newpackage.Basico.Entrenamientos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *  Mostrar el mensaje en la consola. El programa debe abrir y leer el contenido del archivo 
 * de texto «mensaje.txt» y mostrar el mensaje completo en la consola.

2
Implementar manejo de errores y excepciones apropiadas.

3
Utilizar clases y métodos para organizar el código de manera efectiva, 
* centrándote específicamente en la tarea de mostrar el mensaje en la consola.
 * 
 */
public class Entrenamiento1 {
     public static void main(String[] args) {
        try {
            mostrarMensajeDesdeArchivo("mensaje.txt");
        } catch (IOException e) {
            // Manejo de errores
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
 
    private static void mostrarMensajeDesdeArchivo(String nombreArchivo) 
throws IOException {
        File archivo = new File(mensaje.txt);
 
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}