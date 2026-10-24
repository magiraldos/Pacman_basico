package pacman.modelo;

/**
El personaje que controla el jugador
 
 Usa un "buffer de input": cuando el jugador aprieta una tecla, la direccion
 se guarda en direccionSolicitada.
 Recien en mover() se chequea si se puede girar hacia ahi (no hay pared). 
 Si no se puede, sigue en la direccion actual.
 */
public class PacMan extends Personaje {

    // Ultima direccion pedida por el jugador (todavia no necesariamente aplicada)
    private Direccion direccionSolicitada;

    /**
      @param posicionInicial celda donde arranca PacMan
     */
    public PacMan(Posicion posicionInicial) {
        super(posicionInicial, Direccion.IZQUIERDA);
        this.direccionSolicitada = this.direccion;
    }

    /*
      Guarda la direccion que pidio el jugador. No mueve nada todavia.
     */
    public void cambiarDireccion(Direccion direccion) {
        this.direccionSolicitada = direccion;
    }

    /*
     1) Intenta moverse en direccionSolicitada. Si esa celda es transitable,
        actualiza direccion y se mueve
     2) Si no (hay pared) sigue en la direccion actual (si tampoco se puede, se queda)
     */
    @Override
    public void mover(Laberinto laberinto) {
        // Intento girar hacia la direccion solicitada
        Posicion candidata = posicion.siguiente(direccionSolicitada);
        if (laberinto.esTransitable(candidata)) {
            direccion = direccionSolicitada;
            posicion = candidata;
            return;
        }

        // No se puede girar: sigo en la direccion actual
        Posicion siguiente = posicion.siguiente(direccion);
        if (laberinto.esTransitable(siguiente)) {
            posicion = siguiente;
        }
        // Si tampoco es transitable, PacMan se queda quieto contra la pared
    }
}