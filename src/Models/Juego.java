package Models;

import Helpers.GestorArchivos;
import java.util.Scanner;
public class Juego {

    private int misionesCompletadas = GestorArchivos.leerMisionesCompletadas();
    
    private MisionIntermedia misionInt = new MisionIntermedia (1);
    private MisionFinal misionFinal = new MisionFinal ();
    private Mapa mapa = new Mapa(7,7);
    
    // Mision 1: Matriz 7x7
    // Mision 2: Matriz 9x9
    // Mision 3: Pokemon
    
    public void Iniciar() {

        // carga de misiones completadas desde archivo.
        // si el archivo está vacío, queda como está, osea en 0.
        // lectura de misiones completadas.
        // menu principal
        var opcionMenu = menuPrincipal();
        switch (opcionMenu) {
            case 1:
                System.out.println("Iniciando misión...");
                if (misionesCompletadas < 2){
                    misionInt = new MisionIntermedia(misionesCompletadas);
                    mapa = new Mapa(misionInt.Filas, misionInt.Columnas);
                    mapa.CargarPersonajes();
                    mapa.CargarItems();
                    mapa.Mostrar(misionInt.Filas, misionInt.Columnas);
                }
                else{
                    misionFinal = new MisionFinal();
                }
                
                break;
            case 2:
                System.out.println("Guardando estado...");
                break;
            case 3:
                System.out.println("Cargando estado...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
        // según lo que seleccione, seguimos.    
    }

    private int menuPrincipal() {

        Scanner scanner = new Scanner(System.in);

        var opcionInvalida = true;
        
        System.out.println("========== Metal Gear Console ==========");
            System.out.println("           Escape de la base            ");
            System.out.println("----------------------------------------");
            System.out.println();
            System.out.print("Selecciona una opción: ");
            System.out.println();
            System.out.println("1. Iniciar Misión");
            System.out.println("2. Guardar Estado");
            System.out.println("3. Cargar Estado");
            System.out.println();
            System.out.println("----------------------------------------");
            
        while (opcionInvalida) {
            
            int opcion = scanner.nextInt();

            if (1 <= opcion && opcion <= 3) {

                scanner.close();
                opcionInvalida = false;
                return opcion;
            }

            System.out.print("Opcion incorrecta.");
            System.out.println();
        }
        
        return 0;
    }    
}
