package pacman.modelo;

// Clase abstracta
// PuntoComun y SuperPunto heredan de acá
public abstract class Punto {
    protected int valor;  // puntos que suma al ser consumido

    // Consume el punto: suma puntaje a la partida
    public void consumir(Partida partida) {
        partida.sumarPuntaje(this.valor);
    }

    public int getValor() {
        return valor;
    }
}
