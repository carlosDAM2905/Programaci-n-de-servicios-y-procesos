package processBuilderYProcess.ExitValue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExitValue {
    public static void main(String[] args) {

        // Definición de comandos a ejecutar. Cada comando será ejecutado por el programa.

        // comando1: Abre el Bloc de notas (Notepad) en Windows.
        String comando1 = "notepad.exe";

        // comando2: Abre la calculadora en Windows.
        String comando2 = "calc";

        // comando3: Lista el contenido del directorio C:\Users\wicar\Desktop\DAM
        // usando el comando 'dir' en Windows.
        String comando3 = "cmd /c dir C:\\Users\\wicar\\Desktop\\DAM";

        // comando4: Ejecuta 'ls' en Bash (útil si estás en WSL o Linux).
        String comando4 = "bash -c ls";

        // comando5: Este comando es incorrecto (no existe) para simular un error.
        String comando5 = "comandoIncorrecto";


        // Creamos una lista de comandos para que el programa los ejecute uno por uno.
        ArrayList<String> listaComandos = new ArrayList<>();
        listaComandos.add(comando1);  // Añadimos el comando para abrir Notepad.
        listaComandos.add(comando2);  // Añadimos el comando para abrir la calculadora.
        listaComandos.add(comando3);  // Añadimos el comando para listar el contenido de DAM.
        listaComandos.add(comando4);  // Añadimos el comando para ejecutar 'ls' en Bash.
        listaComandos.add(comando5);  // Añadimos un comando que generará un error.

        // Creamos un archivo de texto donde redirigiremos la salida del comando3.
        // En este archivo se guardará el listado del contenido de la carpeta DAM.
        File fichero = new File("src/processBuilderYProcess/ExitValue/listado.txt");

        // Recorremos la lista de comandos uno por uno.
        for (String comando : listaComandos) {
            // Creamos un ProcessBuilder para cada comando. 'split(" ")' separa los argumentos del comando.
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));

            try {
                // Si el comando es el comando3 (el que lista la carpeta DAM), redirigimos la salida al archivo.
                if (comando.equals(comando3)) {
                    pb.redirectOutput(fichero);  // Redirigimos la salida del comando al archivo listado.txt.
                }

                // Iniciamos el proceso (ejecutamos el comando).
                Process proceso = pb.start();

                // Esperamos a que el proceso termine. 'waitFor()' bloquea hasta que el proceso termina.
                proceso.waitFor();

                // Guardamos el código que proporciona el método exitValue() de la clase Process:
                int exitCode = proceso.exitValue();

                // Mostramos el código de salida del proceso.
                // Un código de salida 0 significa que el proceso terminó correctamente.
                System.out.println("Comando: " + comando + " | Código de salida: " + exitCode);

            } catch (IOException e) {
                // Capturamos el error si el comando no existe o no puede ejecutarse.
                System.out.println("Error ejecutando el comando: " + comando);
                // Mostramos la descripción del error.
                System.out.println("Descripción del error: " + e.getMessage());
            } catch (InterruptedException e) {
                // Capturamos la excepción si el proceso es interrumpido mientras está ejecutándose.
                System.out.println("El proceso fue interrumpido: " + comando);
            }
        }
    }
}

