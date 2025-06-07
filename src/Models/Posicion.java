package Models;

public class Posicion {

    private int x = 0;
    private int y = 0;

    public Posicion(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public int GetX() {
        return x;
    }

    public int GetY() {
        return y;
    }

    public void SetX(int _x) {
        x = _x;
    }

    public void SetY(int _y) {
        y = _y;
    }
}
