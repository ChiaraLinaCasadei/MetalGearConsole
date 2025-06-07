package Models;

import java.util.Random;

public class Mapa {

    public Celda[][] mapa;

    private int filas = 0;
    private int columnas = 0;
    private int mision;
    private Snake snake = new Snake();
    private Celda hangar = null;
    private Celda puertaBloqueada = null;
    private Celda[] ubicacionCeldaGuardias = new Celda[4];
    private int cantidadGuardias = 0;

    private Random random = new Random();

    public Celda[][] getMapa() {
        return mapa;
    }

    public void setMapa(Celda[][] mapa) {
        this.mapa = mapa;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Celda getHangar() {
        return hangar;
    }

    public void setHangar(Celda hangar) {
        this.hangar = hangar;
    }

    public Celda getPuertaBloqueada() {
        return puertaBloqueada;
    }

    public void setPuertaBloqueada(Celda puertaBloqueada) {
        this.puertaBloqueada = puertaBloqueada;
    }

    public Celda[] getUbicacionCeldaGuardias() {
        return ubicacionCeldaGuardias;
    }

    public void setUbicacionCeldaGuardias(Celda[] ubicacionCeldaGuardias) {
        this.ubicacionCeldaGuardias = ubicacionCeldaGuardias;
    }

    public int getCantidadGuardias() {
        return cantidadGuardias;
    }

    public void setCantidadGuardias(int cantidadGuardias) {
        this.cantidadGuardias = cantidadGuardias;
    }

    // Crea el mapa según las filas y columnas, y lo llena en base a la misión, que deduce por la cantidad de filas y columnas.
    public Mapa(int _filas, int _columnas) {
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

        if (mision == 1) {
            mapa[0][3].setContenido("H"); // Hangar
            hangar = mapa[0][3];
        } else {
            mapa[1][8].setContenido("P"); // Puerta Bloqueada
            puertaBloqueada = mapa[1][8];
        }
    }

    public Boolean MapasIguales(Celda[][] otroMapa) {

        var mapasIguales = true;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (this.mapa[i][j] != otroMapa[i][j]) {
                    System.out.println("Hay una celda distinta en el mapa. No son iguales.");
                    return false;
                }
            }
        }

        return mapasIguales;
    }

    private void colocarPersonaje(String personaje, int cantidad) {

        if (personaje == "S") {
            var col = 0;
            if (mision == 2) {
                col = columnas - 1;
            }
            mapa[mapa.length - 1][col] = new Celda("S", new Posicion(col, mapa.length - 1));
            snake.posicion = mapa[mapa.length - 1][col].getPosicion();
            mapa[mapa.length - 1][col].setPersonaje(snake);
        } else {
            for (int i = 0; i < cantidad; i++) {

                ubicacionCeldaGuardias[i] = colocarPseudoAleatoriamente(personaje);
                //System.out.println("Se cargó inicialmente un guardia: ");
                //System.out.println("ubicacionCeldaGuardias: "+ i + " guardia en posición: "+ ubicacionCeldaGuardias[i].toString());
            }
        }
    }

    private void moverPersonaje(Personaje personaje) {

        /*if (personaje. == "S"){
            
        }*/
    }

    // Sirve para colocar inicialemente los guardias y los items
    // Los guardias no pueden colocarse inicialmente a menos de 2 celdas de Snake
    // Los guardias no pueden colocarse donde está otro personaje.
    private Celda colocarPseudoAleatoriamente(String simbolo) {

        int intentosMaximos = filas * columnas; // tiene que revisar todas las celdas del mapa
        int intentos = 0;

        while (intentos < intentosMaximos) {
            int filaAleatoria = random.nextInt(filas);
            int columnaAleatoria = random.nextInt(columnas);

            if (mapa[filaAleatoria][columnaAleatoria].getContenido() == " ") {
                var guardia = new Guardia();
                guardia.posicion = mapa[filaAleatoria][columnaAleatoria].getPosicion();

                if (simbolo == "*" && !EnPerimetroSnake(guardia.posicion, 2)) {
                    mapa[filaAleatoria][columnaAleatoria] = new Celda(simbolo, new Posicion(columnaAleatoria, filaAleatoria));
                    mapa[filaAleatoria][columnaAleatoria].setPersonaje(guardia);
                    return mapa[filaAleatoria][columnaAleatoria];
                } else if (simbolo != "*") { //Item
                    mapa[filaAleatoria][columnaAleatoria] = new Celda(simbolo, new Posicion(columnaAleatoria, filaAleatoria));
                    return mapa[filaAleatoria][columnaAleatoria];
                }
            }
            intentos++;
        }

        System.out.println("No se pudo colocar el símbolo '" + simbolo + "'. No hay celdas vacías.");
        return new Celda("!", new Posicion(0, 0));
    }

    public Boolean EnPerimetroSnake(Posicion posicionGuardia, int perimetro) {
        var guardiaX = posicionGuardia.GetX();
        var guardiaY = posicionGuardia.GetY();
        var snakeX = snake.posicion.GetX();
        var snakeY = snake.posicion.GetY();

        int distanciaX = Math.abs(snakeX - guardiaX);
        int distanciaY = Math.abs(snakeY - guardiaY);

        return distanciaX <= perimetro && distanciaY <= perimetro;
    }

    public void CargarItems() {

        if (mision == 1) {
            colocarPseudoAleatoriamente("L");
        } else {
            colocarPseudoAleatoriamente("C4");
        }
    }

    public void CargarPersonajes() {

        // Snake
        colocarPersonaje("S", 1);

        // Guardias (3 para mision 1, 4 para mision 2)
        cantidadGuardias = 3;
        if (filas == 9 && columnas == 9) {
            cantidadGuardias = 4;
        }

        colocarPersonaje("*", cantidadGuardias);
    }

    public void Mostrar(int filas, int columnas) {
        // Línea superior
        for (int j = 0; j < columnas; j++) {
            System.out.print("------");
        }
        System.out.println("-");

        // Filas del tablero
        for (int i = 0; i < filas; i++) {
            // Contenido de la fila
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j].getContenido() == "C4") {
                    System.out.print("|  " + mapa[i][j].getContenido() + " ");
                } else {
                    System.out.print("|  " + mapa[i][j].getContenido() + "  ");
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

    public void MoverGuardias() {

        for (int i = 0; i < cantidadGuardias; i++) {
            var x = ubicacionCeldaGuardias[i].getPosicion().GetX();
            var y = ubicacionCeldaGuardias[i].getPosicion().GetY();

            System.out.println("Se encontro guardia en posicion: " + "fila:" + y + " columna:" + x);
            var guardia = mapa[y][x].getPersonaje();
            if (guardia != null) {
                var respMovimiento = guardia.Mover("", mapa);
                ubicacionCeldaGuardias[i] = respMovimiento.nuevaPosicionPersonaje;
                mapa = respMovimiento.mapaActualizado;
            } else {
                System.out.println("Se perdió guardia en posicion: " + y + " " + x);
            }

        }
    }
}
