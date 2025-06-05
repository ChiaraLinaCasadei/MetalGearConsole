package Models;

import Helpers.GestorArchivos;
import java.util.Scanner;

public class Juego {

    private MisionIntermedia misionInt = new MisionIntermedia(1);
    private MisionFinal misionFinal = new MisionFinal();

    // Mision 1: Matriz 7x7
    // Mision 2: Matriz 9x9
    // Mision 3: Pokemon
    public void Iniciar() {

        // carga de misiones completadas desde archivo.
        // si el archivo está vacío, queda como está, osea en 0.
        // lectura de misiones completadas.
        // menu principal
        var mostrarMenu = true;
        Scanner scanner = new Scanner(System.in);
        while (mostrarMenu) {
            var opcionMenu = menuPrincipal();
            switch (opcionMenu) {
                case 1:
                    System.out.println("Iniciando misión...");
                    misionInt = new MisionIntermedia(0);
                    mostrarMenu = misionInt.Iniciar();
                    break;
                case 2:
                    System.out.println("Ingresa el código de la misión que ganaste para guardar el progreso:");
                    var codigoMision = scanner.nextLine();
                    GestorArchivos.EscribirArchivo(codigoMision);
                    break;
                case 3:
                    String misionCompletada = GestorArchivos.LeerMisionesCompletadas();
                    switch (misionCompletada) {
                        case "MISSION1PASSED":
                            misionInt = new MisionIntermedia(1);
                            mostrarMenu = misionInt.Iniciar();
                            break;
                        case "MISSION2PASSED":
                            misionFinal = new MisionFinal();
                            mostrarMenu = misionFinal.Iniciar();
                            break;
                        case "MISSION3PASSED":
                            System.out.print("Felicitaciones!! Terminaste MetalGear!!");
                        default:
                            System.out.print("Aún no se guardó ningun progreso.");
                            System.out.println("Presiona cualquier tecla para volver al menú: ");
                            scanner.nextLine();
                            break;
                    }
                    break;
                case 4:
                    mostrarMenu = false;
                    System.out.println("Nos vemos la próxima!!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
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
        System.out.println("4. Salir");
        System.out.println();
        System.out.println("----------------------------------------");

        while (opcionInvalida) {

            int opcion = scanner.nextInt();

            if (1 <= opcion && opcion <= 4) {
                opcionInvalida = false;
                return opcion;
            }

            System.out.print("Opcion incorrecta.");
            System.out.println();
        }

        return 0;
    }
}
