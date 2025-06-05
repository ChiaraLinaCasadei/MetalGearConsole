package Models;

import Helpers.ResponseMovimiento;

public abstract class Personaje {

    public Posicion posicion;

    public abstract ResponseMovimiento Mover(String direccion, Celda[][] celdas);
}
