package Helpers;

import Models.Celda;

public class ResponseMovimiento {

    public Celda[][] mapaActualizado;
    public Celda nuevaPosicionPersonaje;
    public Boolean snakeEnLimiteMapa = false;

    public ResponseMovimiento(Celda[][] _mapaActualizado, Celda _nuevaPosicionPersonaje) {
        mapaActualizado = _mapaActualizado;
        nuevaPosicionPersonaje = _nuevaPosicionPersonaje;
    }
}
