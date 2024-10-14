package processBuilderYProcess.listarProgramas.version2;

import java.io.*;

public class Main2 {
    public static void main(String[] args) {

        ProcessBuilder pb = null;
        String ruta;

        if (System.getProperty("os.name").contains("Windows")) {

            pb = new ProcessBuilder("wmic", "product", "get", "name,version");
            ruta = "src\\processBuilderYProcess\\listarProgramas\\version2\\ficheroDeSalida.txt";

        } else {

            pb = new ProcessBuilder("find", "/usr/bin", "/usr/local/bin", "-type", "f", "-executable");
            ruta = "src/processBuilderYProcess/listarProgramas/version2/ficheroDeSalida.txt";
        }


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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {

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
            System.out.println("Código de salida: " + codigoSalida);


        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido el proceso " + e.getMessage());
        }


    }
}
