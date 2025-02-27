package Server;

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

            dataOutputStream.writeUTF("¡Bienvenido!\n¿Es usted un Triangulo Equilatero (pulse 1) o un Cuadrado (pulse 2)?");
            int clientInput = dataInputStream.readInt();

            if (clientInput == 1) {

                dataOutputStream.writeUTF("Indique por favor la base con un numero entero:");
                int base = dataInputStream.readInt();
                dataOutputStream.writeUTF("Indique por favor la altura con un numero entero:");
                int altura = dataInputStream.readInt();
                int area = (base * altura) / 2;
                int perimetro = base*3;
                dataOutputStream.writeUTF("Area = " + area + "\nPerimetro = " + perimetro);

            } else if (clientInput == 2) {

                dataOutputStream.writeUTF("Indique por favor el primer lado con numero entero:");
                int lado1 = dataInputStream.readInt();
                dataOutputStream.writeUTF("Indique por favor el segundo lado con numero entero:");
                int lado2 = dataInputStream.readInt();
                int area = lado1 * lado2;
                int perimetro = lado1 * 2 + lado2 * 2;
                dataOutputStream.writeUTF("Area = " + area + "\nPerimetro = " + perimetro);

            } else {

                dataOutputStream.writeUTF("Opción no valida");

            }
        } catch (IOException err) {

            System.err.printf("Error: %s\n", err.getMessage());

        }
    }
}
