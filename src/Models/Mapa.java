package Models;

import java.util.Random;

public class Mapa {
      
    private Celda[][] celdas;
    private int filas = 0;
    private int columnas = 0;
    private int mision;
    private Random random = new Random();
    
    public Mapa (int _filas, int _columnas){
        filas = _filas;
        columnas = _columnas;
        mision = (filas == 7 && columnas == 7) ? 1 : 2;
        
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                var celda = new Celda();
                celda.Contenido = " ";
                celdas[i][j] = celda;
            }
        }
        
        if (mision == 1){
            celdas[0][3].Contenido = "H";
        }
        else{
            celdas[1][8].Contenido = "P";
        }
    }
    private void colocarPersonaje(String personaje, int cantidad){
        
        if (personaje == "S"){
            var col = 0;
            if (mision == 2){
                col = columnas-1;
            }
            celdas[celdas.length-1][col].Contenido = "S";
        }
        else{
            for (int i = 0; i < cantidad; i++){
                colocarAleatoriamente(personaje);
            }
        }
    }
    
    private void moverPersonaje(){
        
    }
    
    private void colocarAleatoriamente (String simbolo){
        int intentosMaximos = filas * columnas;
        int intentos = 0;

        while (intentos < intentosMaximos) {
            int filaAleatoria = random.nextInt(filas);
            int columnaAleatoria = random.nextInt(columnas);

            if (celdas[filaAleatoria][columnaAleatoria].Contenido == " ") {
                celdas[filaAleatoria][columnaAleatoria].Contenido = simbolo;
                return;
            }
            intentos++;
        }

        System.out.println("No se pudo colocar el símbolo '" + simbolo + "'. No hay celdas vacías.");        
    }
    
    public void CargarItems (){
        
        if (mision == 1){
            colocarAleatoriamente("L");
        }
        else{
            colocarAleatoriamente("C4");
        }        
    }
    
    public void CargarPersonajes (){
        
        // Snake
        colocarPersonaje("S", 1);
        
        // Guardias (3 para mision 1, 4 para mision 2)
        var cantGuardias = 0;
        if (filas == 7 && columnas == 7){
            cantGuardias = 3;
        }
        else{
            cantGuardias = 4;
        }
        
        colocarPersonaje("*", cantGuardias);
    }   
    public void Mostrar(int filas, int columnas){
        // Línea superior
        for (int j = 0; j < columnas; j++) {
            System.out.print("------");
        }
        System.out.println("-");
        
        // Filas del tablero
        for (int i = 0; i < filas; i++) {
            // Contenido de la fila
            for (int j = 0; j < columnas; j++) {
                if (celdas[i][j].Contenido == "C4"){
                    System.out.print("|  " + celdas[i][j].Contenido + " ");
                }
                else{
                    System.out.print("|  " + celdas[i][j].Contenido + "  ");   
                }                
            }
            System.out.println("|");

            // Línea inferior de esa fila
            for (int j = 0; j < columnas; j++) {
                System.out.print("------");
            }
            System.out.println("-");
        }
        
    }
}
