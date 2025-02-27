import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Programa: Introduce la ruta del ejecutable:");
            String ruta = sc.next();
            System.out.println("Programa: Introduce el tiempo de espera en segundos");
            int espera = sc.nextInt();
            Hilo hilo = new Hilo(ruta, espera);
            hilo.start();
        } catch (InputMismatchException err) {
            System.err.println("Introduzca solamente números");
            System.err.println(err.getMessage());
        }
    }
}