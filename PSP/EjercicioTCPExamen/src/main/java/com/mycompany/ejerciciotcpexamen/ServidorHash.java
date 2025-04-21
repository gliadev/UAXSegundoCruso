package com.mycompany.ejerciciotcpexamen;


import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gliatem
 */
public class ServidorHash {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(7445);
        System.out.println("Servidor de Hash escuchando en el puerto " + servidor.getLocalPort() + " ...");

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            String tipoHash = entrada.readLine(); // "1", "2" o "3"
            salida.println("OK");

            String texto = entrada.readLine(); // el texto a hashear

            String hashCalculado = switch (tipoHash) {
                case "1" -> md5(texto);
                case "2" -> sha1(texto);
                case "3" -> sha256(texto);
                default -> "Tipo de hash no válido";
            };

            salida.println(hashCalculado);
            cliente.close();
        }
    }

    public static String md5(String texto) {
        return getHash(texto, "MD5");
    }

    public static String sha1(String texto) {
        return getHash(texto, "SHA-1");
    }

    public static String sha256(String texto) {
        return getHash(texto, "SHA-256");
    }

    private static String getHash(String texto, String tipo) {
        try {
            MessageDigest md = MessageDigest.getInstance(tipo);
            byte[] hash = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error en el algoritmo de hash";
        }
    }
}
