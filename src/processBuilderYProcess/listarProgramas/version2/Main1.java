package processBuilderYProcess.listarProgramas.version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1 {
    public static void main(String[] args) {
        //wmic product get name,version
        // /usr/bin /usr/local/bin -type f -executable


        ProcessBuilder pb = null;

        if (System.getProperty("os.name").contains("Windows")) {

            pb = new ProcessBuilder("wmic", "product", "get", "name,version");

        } else {

            pb = new ProcessBuilder("/usr/bin", "/usr/local/bin", "-type", "f", "-executable");
        }

        try {
            Process proceso = pb.start();

            try (BufferedReader brError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {

                String lineaError;
                while ((lineaError = brError.readLine()) != null) {
                    System.out.println(lineaError);
                }
            }


            try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }


            int exitCode = proceso.waitFor();
            System.out.println("Código de salida: " + exitCode);


        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error, proceso interrumpido " + e.getMessage());
        }


    }
}





