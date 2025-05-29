package Models;

import java.util.Random;

public class Mapa {
      
    public Celda[][] mapa;
            
    private int filas = 0;
    private int columnas = 0;
    private int mision;
    public Snake snake = new Snake();
    public Celda[] posicionGuardias;
    
    
    private Random random = new Random();
    
    // Crea el mapa según las filas y columnas, y lo llena en base a la misión, que deduce por la cantidad de filas y columnas.
    
    public Mapa (int _filas, int _columnas){
        filas = _filas;
        columnas = _columnas;
        mision = (filas == 7 && columnas == 7) ? 1 : 2;
        
        mapa = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                var celda = new Celda(" ", new Posicion(j, i));
                mapa[i][j] = celda;
            }
        }
        
        if (mision == 1){
            mapa[0][3].Contenido = "H"; // Hangar
        }
        else{
            mapa[1][8].Contenido = "P"; // Puerta Bloqueada
        }
    }
    private void colocarPersonaje(String personaje, int cantidad){
        
       if (personaje == "S"){
            var col = 0;
            if (mision == 2){
                col = columnas-1;
            }
            mapa[mapa.length-1][col] = new Celda("S", new Posicion(col, mapa.length-1));
            snake.posicion = mapa[mapa.length-1][col].posicion;
            mapa[mapa.length-1][col].SetPersonaje(snake);          
        }
        else{
            for (int i = 0; i < cantidad; i++){
                colocarPseudoAleatoriamente(personaje);
            }
        }
    }
    
    private void moverPersonaje(Personaje personaje){
        
        /*if (personaje. == "S"){
            
        }*/
    
    }
    
    // Sirve para colocar inicialemente los guardias y los items
    // Los guardias no pueden colocarse inicialmente a menos de 2 celdas de Snake
    
    private Celda colocarPseudoAleatoriamente (String simbolo){
               
        int intentosMaximos = filas * columnas; // tiene que revisar todas las celdas del mapa
        int intentos = 0;

        while (intentos < intentosMaximos) {
            int filaAleatoria = random.nextInt(filas);
            int columnaAleatoria = random.nextInt(columnas);
            
            if (mapa[filaAleatoria][columnaAleatoria].Contenido == " ") {
                var celda = new Celda (simbolo, new Posicion(columnaAleatoria, filaAleatoria));
                var guardia = new Guardia();
                guardia.posicion = celda.posicion;
                if (simbolo == "*" && !guardiaEnPerimetroSnake(guardia.posicion)){                    
                    mapa[filaAleatoria][columnaAleatoria].SetPersonaje(guardia);
                    mapa[filaAleatoria][columnaAleatoria] = celda;
                    return mapa[filaAleatoria][columnaAleatoria];
                }
                else if (simbolo != "*"){ //Item
                 mapa[filaAleatoria][columnaAleatoria] = celda;                
                 return mapa[filaAleatoria][columnaAleatoria];
                }
            }
            intentos++;
        }

        System.out.println("No se pudo colocar el símbolo '" + simbolo + "'. No hay celdas vacías.");
        return new Celda("!", new Posicion(0,0));
    }
    
    private Boolean guardiaEnPerimetroSnake(Posicion posicionGuardia){
        var guardiaX = posicionGuardia.GetX();
        var guardiaY = posicionGuardia.GetY();
        var snakeX = snake.posicion.GetX();
        var snakeY = snake.posicion.GetY();
        
        int distanciaX = Math.abs(snakeX - guardiaX);
        int distanciaY = Math.abs(snakeY - guardiaY);
        
        return distanciaX <= 1 && distanciaY <= 1 && (distanciaX != 0 || distanciaY != 0);
    }
    public void CargarItems (){ 
        
        if (mision == 1){
            colocarPseudoAleatoriamente("L");
        }
        else{
            colocarPseudoAleatoriamente("C4");
        }        
    }
    
    public void CargarPersonajes (){
        
        // Snake
        colocarPersonaje("S", 1);
        
        // Guardias (3 para mision 1, 4 para mision 2)
        var cantGuardias = 3;
        if (filas == 9 && columnas == 9){
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
                if (mapa[i][j].Contenido == "C4"){
                    System.out.print("|  " + mapa[i][j].Contenido + " ");
                }
                else{
                    System.out.print("|  " + mapa[i][j].Contenido + "  ");   
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
