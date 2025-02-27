/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto06comunicacionservicioftp;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.io.IOException;


/**
 *
 * @author gliatem
 */
public class FtpService {
    private static final FTPClient ftp = new FTPClient();

    /**
     * Conecta al servidor FTP.
     */
    public static boolean connect(String host, String user, String pass) throws IOException {
        ftp.connect(host);
        return ftp.login(user, pass);
    }

    /**
     * Busca y elimina un archivo si existe en el servidor FTP.
     */
    public static void searchAndDeleteFile(String fileName) throws IOException {
        FTPFile[] files = ftp.listFiles();
        boolean found = false;

        for (FTPFile file : files) {
            if (file.getName().equalsIgnoreCase(fileName)) {
                found = true;
                if (ftp.deleteFile(file.getName())) {
                    System.out.println("✅ Archivo eliminado con éxito: " + fileName);
                } else {
                    System.err.println("❌ No se pudo eliminar el archivo: " + fileName);
                }
            }
        }

        if (!found) {
            System.out.println("ℹ️ No se encontró el archivo: " + fileName);
        }
    }

    /**
     * Desconecta del servidor FTP.
     */
    public static void disconnect() throws IOException {
        if (ftp.isConnected()) {
            ftp.logout();
            ftp.disconnect();
            System.out.println("✅ Desconectado del servidor FTP.");
        }
    }
}
    

