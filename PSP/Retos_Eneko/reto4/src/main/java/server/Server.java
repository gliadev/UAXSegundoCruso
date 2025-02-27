package server;

import model.Rectangulo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1312)) {

            Socket client = server.accept();
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            Rectangulo rectangulo = new Rectangulo();

            rectangulo.setLado1(dataInputStream.readInt());
            rectangulo.setLado2(dataInputStream.readInt());

            dataOutputStream.writeUTF(rectangulo.toString());

        } catch (IOException err) {

            System.err.printf("Error: %s\n", err.getMessage());

        }
    }
}