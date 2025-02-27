import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class Main {

    private static final FTPClient ftp = new FTPClient();

    public static void main(String[] args) {

        final String HOST = "10.13.24.35";
        final String USER = "alumno";
        final String PASS = "yaquedamenos";

        try {
            ftp.connect(HOST);
            boolean login = ftp.login(USER, PASS);

            if (!login) {
                System.err.println("Login incorrecto");
            } else {
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                System.out.println(ftp.printWorkingDirectory());
                FTPFile[] files = ftp.listFiles();
                searchMalware("dameTolDinero.exe", files);
            }

            ftp.disconnect();

        } catch (IOException err) {
            System.err.printf("Error: %s\n", err.getMessage());
        }
    }

    private static void searchMalware(String name, FTPFile[] workingDir) throws IOException {
        for (FTPFile file : workingDir) {
            if (file.getName().equalsIgnoreCase(name)) {
                if (ftp.deleteFile(file.getName())) {
                    System.out.println("Archivo encontrado y eliminado con exito");
                } else {
                    System.err.println("Archivo encontrado pero no ha podido ser borrado");
                }
            }
        }
    }
}
