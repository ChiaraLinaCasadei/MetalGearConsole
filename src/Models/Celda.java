package Models;
public class Celda {
    
    public String Contenido = " ";
    private Personaje personaje;
    
    public Personaje GetPersonaje(){
        return personaje;
    }
    
    private void setPersonaje(Personaje _personaje){
        personaje = _personaje;
    }
}
