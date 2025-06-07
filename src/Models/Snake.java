package Models;

import Helpers.ResponseMovimiento;
import Helpers.ValidacionesMovimiento;

public class Snake extends Personaje {

    public Boolean hasCrucialItem = false;
    private String mensajeLimiteMapa = "Snake no puede salir del mapa!!";
    private int vida = 100;
    private int ataque = 0;
    private int defensa = 0;

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

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
                && celdaNuevaPosicion.getPersonaje() == null;

        if (validacionesPosicion) {

            posicionActual.setPersonaje(null);
            posicionActual.setContenido(" ");

            if (ValidacionesMovimiento.EsItem(celdaNuevaPosicion)) {
                this.hasCrucialItem = true;
            }

            celdaNuevaPosicion.setPersonaje(this);
            celdaNuevaPosicion.setContenido("S");
            posicion.SetX(nuevoX);
            posicion.SetY(nuevoY);

            return new ResponseMovimiento(mapa, celdaNuevaPosicion);
        }

        var movResp = new ResponseMovimiento(mapa, posicionActual);
        movResp.snakeEnLimiteMapa = false;
        return movResp;
    }
}
