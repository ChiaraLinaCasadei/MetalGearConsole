package Models;

import java.util.Scanner;

public class MisionIntermedia extends Mision{
    
    public int filas = 7;
    public int columnas = 7;
    public Mapa mapa;
    
    public MisionIntermedia (int misionesCompletadas){
        
        if (misionesCompletadas < 1){
            filas = 7;
            columnas = 7;
        }
        else{
            filas = 9;
            columnas = 9;
        }
        
        mapa = new Mapa(filas, columnas);
    }
    
    @Override
    public void iniciar(){
        mapa.CargarPersonajes();
        mapa.CargarItems();
        //System.out.print("Movimiento (W/A/S/D): ");
        mapa.Mostrar(filas, columnas);
        
        return;
        
        /*Scanner scanner = new Scanner(System.in);
        boolean juegoActivo = true;

        while (juegoActivo) {
            String entrada = scanner.nextLine().toLowerCase();

            switch (entrada) {
                case "w":
                    var snake = mapa.posicionSnake.GetPersonaje();
                    mapa.mapa = snake.Mover("arriba", mapa.mapa);
                    break;
                case "s":
                    //moverSnakeAbajo();
                    break;
                case "a":
                    //moverSnakeIzquierda();
                    break;
                case "d":
                    //moverSnakeDerecha();
                    break;
                default:
                    System.out.println("Entrada no válida.");
                    continue; // no mover guardias ni verificar si no se movió Snake
            }
            
            mapa.Mostrar(filas, columnas);
            /*moverGuardias();

            if (verificarVictoria()) {
                System.out.println("¡Misión cumplida!");
                juegoActivo = false;
            } else if (verificarDerrota()) {
                System.out.println("¡Te han descubierto! Misión fallida.");
                juegoActivo = false;
            }
        }

        scanner.close();*/
        
    }
}
