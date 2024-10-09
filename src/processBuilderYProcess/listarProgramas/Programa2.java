package processBuilderYProcess.listarProgramas;

import java.io.*;

public class Programa2 {
    public static void main(String[] args) {

        //comando en windows para mostrar programas instalados: wmic product get name,version
        //comando en linux find /usr/bin /usr/local/bin -type f -executable

        ProcessBuilder pb = null;

        if (System.getProperty("os.name").contains("Windows")) {
            pb = new ProcessBuilder("wmic", "product", "get", "name,version");
        } else {
            pb = new ProcessBuilder("find", "/usr/bin", "/usr/local/bin", "-type", "f", "-executable");
        }

        String ruta = "src/processBuilderYProcess/listarProgramas/fichero.txt";
        File fichero = new File(ruta);


        try {
            Process proceso = pb.start();


            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));

            BufferedReader brErrores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));



            String linea;

            //si hay errores, los capturamos y guardamos en el fichero:
            while ((linea = brErrores.readLine()) != null) {
                bw.write(linea + "\n");
            }

            //si no hay errores, se escribe en el fichero la salida de los comandos que hemos ejecutado:
            while ((linea = br.readLine()) != null) {
                bw.write(linea + "\n");

            }

            int exitCode = proceso.exitValue();
            System.out.println("código de salida: " + exitCode);


            br.close();
            bw.close();


        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        }

    }
}
