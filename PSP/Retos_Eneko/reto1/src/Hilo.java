import java.io.IOException;

public class Hilo extends Thread {
    private String ruta;
    private int segundos;

    public Hilo(String ruta, int segundos) {
        this.ruta = ruta;
        this.segundos = segundos;
    }

    public void run() {
        try {
            ProcessBuilder pb = new ProcessBuilder(ruta);
            System.out.printf("Programa: Esperando %d segundos", segundos);
            Thread.sleep(segundos * 1000L);
            Process process = pb.start();
        } catch (IOException | InterruptedException err) {
            System.err.println(err.getMessage());
        }
    }
}
