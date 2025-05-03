/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicioprocesosexamen;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gliatem
 */
public class EjercicioProcesosExamen {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       
      try {
            // Paso 1: Pedimos el nombre del servidor
            System.out.print("Introduce el nombre del servidor (por ejemplo, server.jar): ");
            String servidor = scanner.nextLine();

            // Ejecutamos el servidor
            ProcessBuilder pbServidor = new ProcessBuilder("java", "-jar", "/var/www/html/" + servidor);
            pbServidor.inheritIO(); // para que la salida del proceso se vea en consola
            Process procesoServidor = pbServidor.start();
            System.out.println("Servidor lanzado correctamente...");

            // Paso 2: Esperamos 1 segundo (1000 ms)
            Thread.sleep(1000);

            // Paso 3: Pedimos el nombre del cliente
            System.out.print("Introduce el nombre del cliente (por ejemplo, cliente.jar): ");
            String cliente = scanner.nextLine();

            // Ejecutamos el cliente
            ProcessBuilder pbCliente = new ProcessBuilder("java", "-jar", "/var/www/html/" + cliente);
            pbCliente.inheritIO();
            Process procesoCliente = pbCliente.start();
            System.out.println("Cliente lanzado correctamente...");

        } catch (IOException e) {
            System.err.println("Error al lanzar uno de los procesos: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("La espera fue interrumpida: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
