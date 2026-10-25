package pacman.modelo;

// Cada casillero del tablero. Puede ser pared o camino, y puede tener un punto
public class Celda {
    private boolean pared;   // true = pared, false = camino
    private Punto punto;     // el punto que tiene adentro (null si está vacía)

    // Constructor: recibe si es pared o no y arranca sin punto
    public Celda(boolean pared) {
        this.pared = pared;
        this.punto = null;
    }

    // Devuelve true si NO es pared
    public boolean esTransitable() {
        return !pared;
    }

    // Devuelve true si la celda tiene un punto adentro
    public boolean tienePunto() {
        return punto != null;
    }

    // Pone un punto en la celda (se usa al armar el laberinto)
    public void colocarPunto(Punto punto) {
        this.punto = punto;
    }

    // Saca el punto de la celda y lo devuelve. La celda queda vacía
    public Punto retirarPunto() {
        Punto p = this.punto;
        this.punto = null;
        return p;
    }
}
