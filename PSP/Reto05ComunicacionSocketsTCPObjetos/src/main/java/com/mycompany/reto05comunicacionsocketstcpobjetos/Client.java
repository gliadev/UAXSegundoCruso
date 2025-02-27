/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto05comunicacionsocketstcpobjetos;

/**
 *
 * @author gliatem
 */

import com.mycompany.reto05comunicacionsocketstcpobjetos.Rectangulo;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el primer lado del rectángulo:");
        int lado1 = scanner.nextInt();

        System.out.println("Ingrese el segundo lado del rectángulo:");
        int lado2 = scanner.nextInt();

        try (Socket socket = new Socket("localhost", 6000);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            // Crear objeto Rectangulo y enviarlo al servidor
            Rectangulo rect = new Rectangulo(lado1, lado2);
            output.writeObject(rect);

            // Recibir objeto con resultados
            Rectangulo resultado = (Rectangulo) input.readObject();
            System.out.println("Resultado recibido:");
            System.out.println("Área: " + resultado.getArea());
            System.out.println("Perímetro: " + resultado.getPerimetro());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
