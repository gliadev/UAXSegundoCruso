/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.reto06comunicacionservicioftp;

import java.io.IOException;

/**
 *
 * @author gliatem
 */
public class Reto06ComunicacionServicioFTP {

    public static void main(String[] args) {
        final String HOST = "10.13.24.35";
        final String USER = "alumno";
        final String PASS = "yaquedamenos";

        try {
            // Conectar al servidor FTP
            if (FtpService.connect(HOST, USER, PASS)) {
                System.out.println("✅ Conectado al servidor FTP.");

                // Buscar y eliminar el malware
                FtpService.searchAndDeleteFile("dameTolDinero.exe");

                // Desconectar
                FtpService.disconnect();
            } else {
                System.err.println("❌ Error: No se pudo iniciar sesión en el FTP.");
            }

        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
