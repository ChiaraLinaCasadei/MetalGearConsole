package Models;

import Helpers.ResponseMovimiento;
import Helpers.ValidacionesMovimiento;
import Interfaces.Enemigo;
import java.util.Random;

public class Guardia extends Personaje implements Enemigo {

    @Override
    public ResponseMovimiento Mover(String direccion, Celda[][] mapa) {

        var x = posicion.GetX();
        var y = posicion.GetY();
        var posicionActual = mapa[y][x];
        String[] direcciones = {"arriba", "abajo", "izquierda", "derecha"};

        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            String dir = direcciones[random.nextInt(4)];
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

            //Si la dirección aleatoria del guardia excede los límites del mapa, elige una nueva dirección aleatoria.
            var nuevaPosicion = new Posicion(nuevoX, nuevoY);
            if (ValidacionesMovimiento.EstaFueraDelMapa(nuevaPosicion, mapa)) {
                continue;
            }

            var celdaNuevaPosicion = mapa[nuevoY][nuevoX];
            var validacionesPosicion
                    = !ValidacionesMovimiento.EsItem(celdaNuevaPosicion)
                    && !ValidacionesMovimiento.EsPuerta(celdaNuevaPosicion)
                    && !ValidacionesMovimiento.EsGuardia(celdaNuevaPosicion)
                    && celdaNuevaPosicion.GetPersonaje() == null
                    && celdaNuevaPosicion.Contenido.equals(" ");

            if (validacionesPosicion) {

                posicionActual.SetPersonaje(null);
                posicionActual.Contenido = " ";

                celdaNuevaPosicion.SetPersonaje(this);
                celdaNuevaPosicion.Contenido = "*";
                posicion.SetX(nuevoX);
                posicion.SetY(nuevoY);

                return new ResponseMovimiento(mapa, celdaNuevaPosicion);
            }
        }
        return new ResponseMovimiento(mapa, posicionActual);
    }

}
