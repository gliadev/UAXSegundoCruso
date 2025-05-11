/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciotcpexamen;


import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author gliatem
 */
public class ClienteHash {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el texto a cifrar:");
        String texto = scanner.nextLine();

        System.out.println("Tipo de hash:\n1. MD5\n2. SHA1\n3. SHA256");
        String tipo = scanner.nextLine(); // Se pasa como String ("1", "2", "3")

        Socket socket = new Socket("localhost", 7445);

        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        salida.println(tipo); // Enviar tipo de hash
        String respuesta = entrada.readLine();
        if (!respuesta.equals("OK")) {
            System.out.println("Error del servidor al aceptar el tipo de hash");
            socket.close();
            return;
        }
        System.out.println("Servidor respondio " + respuesta);

        salida.println(texto); // Enviar el texto
        String hashRecibido = entrada.readLine(); // Recibir hash
        System.out.println("Hash recibido: " + hashRecibido);

        socket.close();
    }
}
