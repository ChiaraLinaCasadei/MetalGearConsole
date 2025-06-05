package Helpers;

import Models.Celda;
import Models.Posicion;

public class ValidacionesMovimiento {
    
    public static Boolean EstaFueraDelMapa(Posicion nuevaPosicion, Celda[][] mapa) {

        int maxFilas = mapa.length;
        int maxColumnas = mapa.length;

        int nuevaX = nuevaPosicion.GetX();
        int nuevaY = nuevaPosicion.GetY();

        // Evaluo si está fuera del mapa
        return (nuevaX < 0 || nuevaX >= maxColumnas || nuevaY < 0 || nuevaY >= maxFilas);
    }
    
    public static Boolean EsItem (Celda celda){
        return celda.Contenido == "L" || celda.Contenido == "C4";
    }
    
    public static Boolean EsPuerta (Celda celda){
        return celda.Contenido == "H" || celda.Contenido == "P";
    }
    
    public static Boolean EsGuardia (Celda celda){
        return celda.Contenido == "*";
    }
    
    public static Boolean EsObstaculoGuardia (Celda celda){
        return EsItem(celda) || EsGuardia(celda);
    }
}
