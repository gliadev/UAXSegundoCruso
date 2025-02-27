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
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6000)) { // Se conecta al servidor en el puerto 6000
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            System.out.println(dataInputStream.readUTF()); // Mensaje de bienvenida
            dataOutputStream.writeInt(sc.nextInt()); // Tipo de figura
            
            System.out.println(dataInputStream.readUTF()); // Pregunta 1
            dataOutputStream.writeInt(sc.nextInt()); // Respuesta 1
            
            System.out.println(dataInputStream.readUTF()); // Pregunta 2
            dataOutputStream.writeInt(sc.nextInt()); // Respuesta 2

            System.out.println(dataInputStream.readUTF()); // Resultado final
        } catch (IOException err) {
            System.err.printf("Error: %s\n", err.getMessage());
        } finally {
            sc.close();
        }
    }
}
