package processBuilderYProcess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFichero {
    public static void main(String[] args) {

        escribir();
    }

    public static void escribir() {
        String ruta = "datos.txt";
        File fichero = new File(ruta);

        try (FileWriter fileWriter = new FileWriter(fichero, true)) {

            for (int i = 0; i < 5; i++) {
                fileWriter.append("currupipi\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo " + e.getMessage());
        }
    }
}
