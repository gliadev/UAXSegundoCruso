package Client;

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
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            System.out.println(dataInputStream.readUTF());
            dataOutputStream.writeInt(sc.nextInt());
            System.out.println(dataInputStream.readUTF());
            dataOutputStream.writeInt(sc.nextInt());
            System.out.println(dataInputStream.readUTF());
            dataOutputStream.writeInt(sc.nextInt());

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
}

