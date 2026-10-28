package pacman.modelo;

/*
  Clase abstracta base de todo lo que se mueve en el laberinto (PacMan y los Fantasmas)
 
  Guarda la posicion actual, la posicion inicial (para reiniciar) y la direccion en la que se esta moviendo
 */
public abstract class Personaje {

    // Posicion actual en el laberinto
    protected Posicion posicion;
    // Posicion donde arranca (se usa al reiniciar o cuando lo comen)
    protected Posicion posicionInicial;
    // Direccion en la que se esta moviendo actualmente
    protected Direccion direccion;

    /**
      @param posicionInicial, posicion donde arranca el personaje
      @param direccionInicial, direccion con la que arranca
     */
    public Personaje(Posicion posicionInicial, Direccion direccionInicial) {
        this.posicionInicial = posicionInicial;
        this.posicion = posicionInicial;
        this.direccion = direccionInicial;
    }

    /*
      Mueve al personaje un paso en el laberinto.
      Cada subclase define como decide hacia donde ir.
     */
    public abstract void mover(Laberinto laberinto);

    /*
      Copia posicionInicial a posicion (vuelve al punto de partida).
     */
    public void volverAPosicionInicial() {
        this.posicion = this.posicionInicial;
    }

    /*
      hay colision si ambos personajes estan en la misma posicion
     */
    public boolean colisionaCon(Personaje otro) {
        return this.posicion.equals(otro.getPosicion());
    }

    public Posicion getPosicion() {
        return posicion;
    }
}