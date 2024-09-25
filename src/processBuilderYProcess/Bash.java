package processBuilderYProcess;

import java.io.IOException;

public class Bash {

    // creas una variable que guarda la forma en que se tiene que lanzar el proceso
    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "dir > listado.txt");

        try {
            // el método start() de ProcessBuilder es el que crea el Process
            Process proceso = processBuilder.start();
            System.out.println("¿Esta vivo? " + proceso.isAlive());
            proceso.info();
            proceso.destroy();
            System.out.println(proceso.exitValue());
        } catch (IOException e) {
            System.out.println("Ha habido un error " + e.getMessage());
        }
    }
}
