package processBuilderYProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Bash2 {
    public static
    void main(String[] args) {

        // definimos la ruta donde queremos que se guarde el fichero, en este caso el escritorio de nuestro ordenador:

        String directorioDeGuardado = "C:\\Users\\wicar\\Desktop\\";

        File directorio = new File(directorioDeGuardado);

        // Creamos el ProcessBuilder que ejecutara los comandos cmd, /c, dir y guardará el resultado en un txt que se llamará listado2.txt:

        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "dir > listado2.txt");

        // Con el método directory() le decimos el directorio donde tiene que trabajar el proceso que hemos creado:

        processBuilder.directory(directorio);

        String archivo = directorioDeGuardado + "listado2.txt";

        // Creamos el primer try para que inicie el proceso:

        try {
            Process proceso = processBuilder.start(); // esto inicia el proceso
            proceso.waitFor(); // con waitFor() esperamos a que el proceso finalice para que continue despues ejecutandose el programa. Es decir, nos aseguramos de que el proceso ha terminado de ejecutarse


            // ahora en este bloque try he utilizado try with resources para no tener que cerrar manualmente más tarde el bufferedReader.
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo))) {

            // Declaro un String que almacena cada linea de bufferedReader:
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }
        }

        } catch (Exception e) {
            System.out.println("Ha habido un error " + e.getMessage());
        }
    }
}
