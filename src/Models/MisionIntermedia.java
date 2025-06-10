package Models;

import Helpers.ResponseMovimiento;
import java.util.Scanner;

public class MisionIntermedia extends Mision {

    private int filas = 7;
    private int columnas = 7;
    private Mapa mapa;

    public MisionIntermedia(int misionesCompletadas) {

        if (misionesCompletadas < 1) {
            filas = 7;
            columnas = 7;
        } else {
            filas = 9;
            columnas = 9;
        }

        mapa = new Mapa(filas, columnas);
    }

    @Override
    public Boolean Iniciar() {
        mapa.CargarPersonajes();
        mapa.CargarItems();
        if (filas == 7) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║  MISIÓN 1 - HANGAR DE ENTRADA    ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("Objetivo: Dirigirse a la puerta del hangar. Cuidado con los guardias!");
        } else {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║   MISIÓN 2 - ALMACÉN DE ARMAS    ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("Objetivo: Detonar la puerta bloqueada sin ser oído. Cuidado con los guardias!");
        }
        mapa.Mostrar(filas, columnas);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Movimiento (W/A/S/D): ");
        System.out.println();

        boolean juegoActivo = true;

        while (juegoActivo) {
                       
            String entrada = scanner.nextLine();
            entrada = entrada.toLowerCase();
            ResponseMovimiento movResp;
            switch (entrada) {
                case "w":
                    movResp = mapa.getSnake().Mover("arriba", mapa.mapa);
                    break;
                case "s":
                    movResp = mapa.getSnake().Mover("abajo", mapa.mapa);
                    break;
                case "a":
                    movResp = mapa.getSnake().Mover("izquierda", mapa.mapa);
                    break;
                case "d":
                    movResp = mapa.getSnake().Mover("derecha", mapa.mapa);
                    break;
                default:
                    System.out.println("Entrada no válida.");
                    continue; // no mover guardias ni verificar si no se movió Snake
            }
            if (movResp.snakeEnLimiteMapa) {
                continue;
            }
            mapa.mapa = movResp.mapaActualizado;
            mapa.MoverGuardias();
            mapa.LimpiarConsola();
            mapa.Mostrar(filas, columnas);

            if (EsDerrota()) {
                System.out.println("¡Te han descubierto! Misión fallida.");
                System.out.println("Presiona cualquier tecla para volver al menú: ");
                scanner.nextLine();
                break;
            }
            if (EsVictoria()) {
                System.out.println("¡Misión cumplida!");
                var codigoMision = filas == 7 ? "MISSION1PASSED" : "MISSION2PASSED";
                System.out.println("Este es tu código para guardar tu progreso: " + codigoMision);
                System.out.println("Presiona cualquier tecla para volver al menú: ");
                scanner.nextLine();
                break;
            }
            
            

        }

        //scanner.close();
        return true;

    }

    @Override
    public Boolean EsVictoria() {

        var mision = filas == 7 ? 1 : 2;
        var objetivo = mision == 1 ? mapa.getHangar() : mapa.getPuertaBloqueada();
        var mensajePrimerMision = "El acceso al hangar está cerrado, se necesita una llave...";
        var mensajeSegundaMision = "La puerta está bloqueada, si tan solo pudiéramos volarla con algo...";
        var mensaje = mision == 1 ? mensajePrimerMision : mensajeSegundaMision;

        if (mision == 1) {
            if (mapa.EnPerimetroSnake(objetivo.getPosicion(), 1) && mapa.getSnake().hasCrucialItem) {
                return true;
            }
        } else {
            var enPuertaConExplosivo = mapa.EnPerimetroSnake(objetivo.getPosicion(), 1) && mapa.getSnake().hasCrucialItem;
            if (enPuertaConExplosivo && !GuardiasCerca()) {
                return true;
            }
            if (enPuertaConExplosivo && GuardiasCerca()) {
                System.out.println("Aún no. Los guardias prodrían oír la explosión...");
            }
        }

        if (mapa.EnPerimetroSnake(objetivo.getPosicion(), 1) && !mapa.getSnake().hasCrucialItem) {
            System.out.println(mensaje);
        }

        return false;
    }

    private Boolean GuardiasCerca() {
        int cantGuardias = mapa.getCantidadGuardias();
        for (int i = 0; i < cantGuardias; i++) {
            var posicionGuardia = mapa.getUbicacionCeldaGuardias()[i].getPosicion();
            if (mapa.EnPerimetroSnake(posicionGuardia, 3)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean EsDerrota() {
        int cantGuardias = mapa.getCantidadGuardias();
        for (int i = 0; i < cantGuardias; i++) {
            var posicionGuardia = mapa.getUbicacionCeldaGuardias()[i].getPosicion();
            if (mapa.EnPerimetroSnake(posicionGuardia, 1)) {
                //System.out.println("Snake está en: "+ mapa.snake.posicion.GetX() + " " + mapa.snake.posicion.GetY());
                //System.out.println("Guardia está en: "+ posicionGuardia.GetX() + " " + posicionGuardia.GetY());
                return true;
            }
        }
        return false;
    }
}
