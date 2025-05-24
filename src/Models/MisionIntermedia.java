package Models;
public class MisionIntermedia extends Mision{
    
    public int Filas = 7;
    public int Columnas = 7;
    
    public MisionIntermedia (int misionesCompletadas){
        
        if (misionesCompletadas < 1){
            Filas = 7;
            Columnas = 7;
        }
        else{
            Filas = 9;
            Columnas = 9;
        }
    }
    @Override
    public void iniciar(){
        
    }
}
