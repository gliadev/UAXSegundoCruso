/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciosftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author gliatem
    DONE 1. Connect to an FTP Server
    DONE 2. List Files in an FTP Directory
    DONE 3. Upload a File to an FTP Server
    DONE 4. Download a File from an FTP Server
    DONE 5. Delete a File from an FTP Server
    DONE 6. Rename a File on an FTP Server
    DONE 7. Create and Delete a Directory on an FTP Server
 */
public class FTPClientApp {
    private static final String IP = "134.0.14.63";
    private static final String USER = "pruebas1e";
    private static final String PASS = "07lXPZe@U81H99";
    private static final File URL = new File("test.txt");
    private static final String FILENAME = "index.php";

    public static void main(String[] args) {
         try {
            FTPClient ftp = connectToServer(IP, USER, PASS);
            uploadFile(ftp, URL);
            createDir(ftp, ftp.printWorkingDirectory() + "/test");
            listFiles(ftp);
            downloadFile(ftp, FILENAME);
            renameFile(ftp, URL.getName(), "nombreCambiado.txt");
            deleteFile(ftp, "nombreCambiado.txt");
            deleteDir(ftp, "test");
            ftp.logout();
        } catch (IOException err) {
            System.out.printf("Error: %s\n", err.getMessage());
        }
    }

    private static FTPClient connectToServer(String ip, String user, String pass) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(ip);
        boolean login = ftp.login(user, pass);
        if (login) {
            ftp.changeWorkingDirectory("/web/");
            return ftp;
        } else {
            throw new IOException("Error login into the server");
        }
    }

    private static void listFiles(FTPClient ftp) throws IOException {
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        FTPFile[] fileList = ftp.listFiles();
        for (FTPFile file : fileList) {
            System.out.println(file.getName());
        }
    }

    private static void uploadFile(FTPClient ftp, File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        boolean res = ftp.storeFile(file.getName(), inputStream);
        inputStream.close();
        System.out.println(res ? "Upload successfully" : "Error uploading");
    }

    private static void downloadFile(FTPClient ftp, String filename) throws IOException {
        FTPFile[] fileList = ftp.listFiles();
        for (FTPFile file : fileList) {
            if (Objects.equals(file.getName(), filename)) {
                FileOutputStream outputStream = new FileOutputStream(filename);
                ftp.retrieveFile(file.getName(), outputStream);
                System.out.println("File downloaded successfully");
            }
        }
    }

    private static void deleteFile(FTPClient ftp, String filename) throws IOException {
        FTPFile[] fileList = ftp.listFiles();
        for (FTPFile file : fileList) {
            if (Objects.equals(file.getName(), filename)) {
                ftp.deleteFile(filename);
                System.out.println("File deleted successfully");
            }
        }
    }

    private static void renameFile(FTPClient ftp, String filename, String newName) throws IOException {
        FTPFile[] fileList = ftp.listFiles();
        for (FTPFile file : fileList) {
            if (Objects.equals(file.getName(), filename)) {
                ftp.rename(file.getName(), newName);
                System.out.println("File renamed successfully");
            }
        }
    }

    private static void createDir(FTPClient ftp, String path) throws IOException {
        System.out.println(ftp.makeDirectory(path) ? "Directory created" : "Error creating the directory");
    }

    private static void deleteDir(FTPClient ftp, String dirname) throws IOException {
        FTPFile[] dirList = ftp.listDirectories();
        for (FTPFile dir : dirList) {
            if (Objects.equals(dir.getName(), dirname)) {
                System.out.println(ftp.removeDirectory(dir.getName()) ? "Dir deleted successfully" : "Error deleting the dir");
            }
        }
    }
}



