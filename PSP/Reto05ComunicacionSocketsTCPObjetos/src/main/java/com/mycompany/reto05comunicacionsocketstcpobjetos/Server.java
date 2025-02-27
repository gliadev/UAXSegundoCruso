/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto05comunicacionsocketstcpobjetos;
import com.mycompany.reto05comunicacionsocketstcpobjetos.Rectangulo;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author gliatem
 */




public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6000)) { // Puerto 6000
            System.out.println("Servidor esperando conexiones en el puerto 6000...");

            while (true) { // Permitir múltiples conexiones
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

            // Recibir objeto Rectangulo del cliente
            Rectangulo rect = (Rectangulo) input.readObject();
            System.out.println("Recibido rectángulo: Lado1=" + rect.getLado1() + ", Lado2=" + rect.getLado2());

            // Calcular área y perímetro
            rect.calcular();

            // Enviar el objeto actualizado de vuelta al cliente
            output.writeObject(rect);
            System.out.println("Enviado resultado: Área=" + rect.getArea() + ", Perímetro=" + rect.getPerimetro());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

