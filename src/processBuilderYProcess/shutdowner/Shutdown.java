package processBuilderYProcess.shutdowner;

import java.io.IOException;
import java.util.Scanner;

public class Shutdown {
    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {

        shutdown();

    }

    public static void shutdown() {

        System.out.println("Indica tiempo para realizar la acción (segundos)");
        String tiempo = teclado.nextLine();

        try {
            int tiempoEnSegundos = Integer.parseInt(tiempo);
            if (tiempoEnSegundos < 0) {
                System.out.println("El tiempo no puede ser inferior a 0");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Tiempo incorrecto " + e.getMessage());
            return;
        }

        System.out.println("apagar -> s\nreiniciar -> r\nhibernar -> h");
        String opcion = teclado.nextLine();

        String[] comando = {"cmd","/c","shutdown"};

        switch (opcion) {


            case "s", "r":
                ProcessBuilder pb1 = new ProcessBuilder(comando[0], comando[1], comando[2], "/" + opcion, "/t", tiempo);
                ejecutar(pb1);
                break;

            case "h":
                ProcessBuilder pb2 = new ProcessBuilder(comando[0], comando[1], comando[2], "/" + opcion);
                ejecutar(pb2);
                break;

            default:
                System.out.println("opción no válida");

        }
    }

    public static void ejecutar(ProcessBuilder pb) {
        try {
            Process proceso = pb.start();
            proceso.waitFor();
        } catch (IOException e) {
            System.out.println("Ha habido un error " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El proceso no ha terminado " + e.getMessage());
        }
    }
}
