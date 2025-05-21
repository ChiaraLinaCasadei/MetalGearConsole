/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Scanner;

/**
 *
 * @author chiar
 */
public class Juego {

    private int misionesCompletadas = 0;

    public void Iniciar() {

        // carga de misiones completadas desde archivo.
        // si el archivo está vacío, queda como está, osea en 0.
        // lectura de misiones completadas.
        // menu principal
        var opcionMenu = menuPrincipal();
        switch (opcionMenu) {
            case 1:
                System.out.println("Iniciando misión...");
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
