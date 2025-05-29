package Models;
public class Celda {
    
    public String Contenido = " ";
    private Personaje personaje = null;
    public Posicion posicion = new Posicion (0,0);
    
    
    public Celda (String _contenido, Posicion _posicion){
        Contenido = _contenido;
        posicion = _posicion;
    }
    public Personaje GetPersonaje(){
        return personaje;
    }
    
    public void SetPersonaje(Personaje _personaje){
        personaje = _personaje;
    }
}
