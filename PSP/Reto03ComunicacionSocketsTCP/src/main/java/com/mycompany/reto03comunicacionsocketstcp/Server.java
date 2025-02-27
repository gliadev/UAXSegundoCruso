/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto03comunicacionsocketstcp;

/**
 *
 * @author gliatem
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(6000)) { // Se cambia el puerto a 6000
            System.out.println("Servidor iniciado en el puerto 6000...");
            
            while (true) { // Permite múltiples clientes
                Socket client = server.accept();
                System.out.println("Cliente conectado...");
                
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException err) {
            System.err.printf("Error en el servidor: %s\n", err.getMessage());
        }
    }

    private static void handleClient(Socket client) {
        try (DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())) {

            dataOutputStream.writeUTF("¡Bienvenido!\n¿Es usted un Triángulo Equilátero (pulse 1) o un Rectángulo (pulse 2)?");
            int clientInput = dataInputStream.readInt();

            if (clientInput == 1) {
                dataOutputStream.writeUTF("Indique la base:");
                int base = dataInputStream.readInt();
                dataOutputStream.writeUTF("Indique la altura:");
                int altura = dataInputStream.readInt();
                int area = (base * altura) / 2;
                int perimetro = base * 3;
                dataOutputStream.writeUTF("Área = " + area + "\nPerímetro = " + perimetro);
            } else if (clientInput == 2) {
                dataOutputStream.writeUTF("Indique el primer lado:");
                int lado1 = dataInputStream.readInt();
                dataOutputStream.writeUTF("Indique el segundo lado:");
                int lado2 = dataInputStream.readInt();
                int area = lado1 * lado2;
                int perimetro = 2 * (lado1 + lado2);
                dataOutputStream.writeUTF("Área = " + area + "\nPerímetro = " + perimetro);
            } else {
                dataOutputStream.writeUTF("Opción no válida");
            }
        } catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
        }
    }
}