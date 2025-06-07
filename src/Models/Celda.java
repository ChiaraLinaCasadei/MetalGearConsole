package Models;

public class Celda {

    private String Contenido = " ";
    private Personaje personaje = null;
    private Posicion posicion = new Posicion(0, 0);

    public Celda(String _contenido, Posicion _posicion) {
        Contenido = _contenido;
        posicion = _posicion;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje _personaje) {
        personaje = _personaje;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public String toString() {
        return "Personaje: " + Contenido + "(" + posicion.GetX() + ", " + posicion.GetY() + ")";
    }
}
