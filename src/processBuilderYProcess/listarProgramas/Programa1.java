package processBuilderYProcess.listarProgramas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Programa1 {
    public static void main(String[] args) {

        //comando en windows para mostrar programas instalados: wmic product get name,version
        //comando en linux find /usr/bin /usr/local/bin -type f -executable


        ProcessBuilder pb = null;

        if (System.getProperty("os.name").contains("Windows")) {
            pb = new ProcessBuilder("wmic", "product", "get", "name,version");
        } else {
            pb = new ProcessBuilder("find", "/usr/bin", "/usr/local/bin", "-type", "f", "-executable");
        }

        //creamos el proceso:

        try {
            Process proceso = pb.start();

            InputStream inputStream = proceso.getInputStream(); //con este InputStream conseguimos la información
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream); //con ImputStreamReader convertimos la información a texto
            BufferedReader br = new BufferedReader(inputStreamReader); //con BufferedReader leemos la información que previamente hemos convertido a texto

            InputStream inputStreamErrores = proceso.getErrorStream(); //con este InputStream conseguimos los errores que puedan surgir mientras se ejecuta el proceso
            InputStreamReader inputStreamReaderErrores = new InputStreamReader(inputStreamErrores); //con este InputStreamReader convertimos la salida del InputStream en texto para que pueda ser leído
            BufferedReader brErrores = new BufferedReader(inputStreamReaderErrores); //con este BufferedReader leemos los errores


            //si errores, que me lo diga leyendo línea a línea
            String linea;
            while ((linea = brErrores.readLine()) != null) {
                System.out.println("Error: " + linea + "\n");
            }

            //si no hay errores me mostrará la salida del proceso
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            //capturamos el código de salida y lo mostramos:
            int exitCode = proceso.exitValue();
            System.out.println("Código de salida: " + exitCode);

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        }

    }
}
