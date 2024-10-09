package processBuilderYProcess.listarProgramas.version2;

import java.io.*;

public class Main2 {
    public static void main(String[] args) {

        ProcessBuilder pb = null;

        if (System.getProperty("os.name").contains("Windows")) {

            pb = new ProcessBuilder("wmic", "product", "get", "name,version");

        } else {

            pb = new ProcessBuilder("/usr/bin", "/usr/local/bin", "-type", "f", "-executable");
        }

        String ruta = "src/processBuilderYProcess/listarProgramas/ficheroDeSalida.txt";

        File fichero = new File(ruta);
        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado con éxito");
            } else {
                System.out.println("El fichero ya existe");
            }

        } catch (IOException e) {
            System.out.println("Error al crear el fichero " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {

            Process proceso = pb.start();

            try (BufferedReader brError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {

                String lineaError;
                while ((lineaError = brError.readLine()) != null) {
                    bw.write(lineaError + "\n");

                }
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    bw.write(linea + "\n");
                }
            }

            int codigoSalida = proceso.waitFor();


        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido el proceso " + e.getMessage());
        }


    }
}
