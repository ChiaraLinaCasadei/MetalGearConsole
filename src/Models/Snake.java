package Models;

import Helpers.ResponseMovimiento;
import Helpers.ValidacionesMovimiento;

public class Snake extends Personaje {

    public Boolean hasCrucialItem = false;
    private String mensajeLimiteMapa = "Snake no puede salir del mapa!!";

    @Override
    public ResponseMovimiento Mover(String dir, Celda[][] mapa) {

        var x = posicion.GetX();
        var y = posicion.GetY();
        var posicionActual = mapa[y][x];

        int nuevoX = x;
        int nuevoY = y;
        switch (dir) {
            case "arriba":
                nuevoY = y - 1;
                break;
            case "abajo":
                nuevoY = y + 1;
                break;
            case "izquierda":
                nuevoX = x - 1;
                break;
            case "derecha":
                nuevoX = x + 1;
                break;
        }

        var nuevaPosicion = new Posicion(nuevoX, nuevoY);
        if (ValidacionesMovimiento.EstaFueraDelMapa(nuevaPosicion, mapa)) {
            System.out.println(mensajeLimiteMapa);
            var movResp = new ResponseMovimiento(mapa, posicionActual);
            movResp.snakeEnLimiteMapa = true;
            return movResp;
        }

        var celdaNuevaPosicion = mapa[nuevoY][nuevoX];
        var validacionesPosicion
                = !ValidacionesMovimiento.EsPuerta(celdaNuevaPosicion)
                && celdaNuevaPosicion.GetPersonaje() == null;

        if (validacionesPosicion) {

            posicionActual.SetPersonaje(null);
            posicionActual.Contenido = " ";

            if (ValidacionesMovimiento.EsItem(celdaNuevaPosicion)) {
                this.hasCrucialItem = true;
            }

            celdaNuevaPosicion.SetPersonaje(this);
            celdaNuevaPosicion.Contenido = "S";
            posicion.SetX(nuevoX);
            posicion.SetY(nuevoY);

            return new ResponseMovimiento(mapa, celdaNuevaPosicion);
        }

        var movResp = new ResponseMovimiento(mapa, posicionActual);
        movResp.snakeEnLimiteMapa = false;
        return movResp;
    }
}
