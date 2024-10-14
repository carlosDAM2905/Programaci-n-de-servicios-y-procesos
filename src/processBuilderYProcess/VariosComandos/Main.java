package processBuilderYProcess.VariosComandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {


        ProcessBuilder pb;
        if (System.getProperty("os.name").contains("Windows")) {
            pb = new ProcessBuilder("cmd.exe", "/c", "cd C:\\Program Files && systeminfo && date/T");
        } else {
            pb = new ProcessBuilder("bash", "-c", "cd /bin && uname -a && date");
        }

        try {
            Process proceso = pb.start();

            try (BufferedReader brError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {
                String linea;
                while ((linea = brError.readLine()) != null) {
                    System.out.println(linea + "\n");
                }

            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea + "\n");
                }
            }

            int exitCode = proceso.exitValue();
            System.out.println("Proceso finalizado con código " + exitCode);

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        }


    }
}
