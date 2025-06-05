package Helpers;


import java.io.*;
import java.nio.file.*;

public class GestorArchivos {

    public static String LeerMisionesCompletadas() {
        String misionesCompletadas = null;
        try {
            Path ruta = Paths.get("src/Files/PuntosGuardado.txt");
            BufferedReader reader = Files.newBufferedReader(ruta);

            String linea = reader.readLine();
            reader.close();
            
            // El archivo está vacío al jugar por primera vez.
            // O si se jugó solo la primer misión y no ganó.
            if (linea == null){
                return "NOVICTORIESYET"; 
            }
            
            return linea;
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return misionesCompletadas;
    }

    public static void EscribirArchivo(String contenido) {
        try {
            Path ruta = Paths.get("src/Files/PuntosGuardado.txt");
            BufferedWriter writer = Files.newBufferedWriter(ruta);
            writer.write(contenido);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

}
