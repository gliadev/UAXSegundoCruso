package client;

import model.Rectangulo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1312)) {
            Rectangulo rectangulo = generarRectangulo();
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            System.out.println(dataInputStream.readUTF());
            dataOutputStream.writeInt(rectangulo.getLado1());

            System.out.println(dataInputStream.readUTF());
            dataOutputStream.writeInt(rectangulo.getLado2());

            System.out.println(dataInputStream.readUTF());
        } catch (IOException err) {
            System.err.printf("Error: %s\n", err.getMessage());
        } catch (InputMismatchException err) {
            System.err.println("Se deben introducir solamente numeros enteros");
            sc.nextLine();
        } finally {
            sc.close();
        }
    }

    private static Rectangulo generarRectangulo() throws InputMismatchException {
        System.out.println("Introduzca el lado 1 del rectangulo en numeros enteros:");
        int lado1 = sc.nextInt();
        System.out.println("Introduzca el lado 2 del rectangulo en numeros enteros:");
        int lado2 = sc.nextInt();

        return new Rectangulo(lado1, lado2);
    }
}

