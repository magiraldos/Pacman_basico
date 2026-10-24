package pacman.modelo;

import java.util.List;
import java.util.Random;


public class Fantasma extends Personaje {

    // Puntos que recibe el jugador al comer un fantasma
    public static final int PUNTOS_AL_SER_COMIDO = 200;

    private EstadoFantasma estado;
    // Cantidad de movimientos que le quedan al fantasma en estado ASUSTADO
    private int tiempoAsustado;

    // --- Extras (no estan en el diagrama, hay que coordinarlo mejor) ---
    // Posicion de PacMan a perseguir. La Partida la actualiza con setObjetivo()
    // antes de llamar a mover(), porque mover(Laberinto) no recibe a PacMan.
    private Posicion objetivo;
    private final Random random = new Random();

    /**
     * @param posicionInicial celda donde arranca el fantasma
     */
    public Fantasma(Posicion posicionInicial) {
        super(posicionInicial, Direccion.IZQUIERDA);
        this.estado = EstadoFantasma.NORMAL;
        this.tiempoAsustado = 0;
    }

    /**
     * Le informa al fantasma donde esta PacMan (para que lo persiga).
     *   la Partida deberia llamarlo antes de mover().
     */
    public void setObjetivo(Posicion objetivo) {
        this.objetivo = objetivo;
    }

    /**
     Mueve al fantasma un paso:
     1) actualiza el temporizador de susto
    2) elige direccion segun el estado
    3) se mueve en esa direccion
     */
    @Override
    public void mover(Laberinto laberinto) {
        actualizarTemporizador();

        // Si venia REGRESANDO y ya esta en su casa, vuelve a la normalidad
        if (estado == EstadoFantasma.REGRESANDO && posicion.equals(posicionInicial)) {
            reiniciarEstado();
        }

        List<Direccion> opciones = laberinto.direccionesDisponibles(posicion);

        Direccion elegida;
        switch (estado) {
            case NORMAL:
                // persigue a PacMan (si no sabe donde esta, se mueve al azar)
                elegida = (objetivo != null)
                        ? elegirDireccionHacia(objetivo, opciones)
                        : elegirDireccionAleatoria(opciones);
                break;
            case ASUSTADO:
                // Huye: direccion aleatoria
                elegida = elegirDireccionAleatoria(opciones);
                break;
            default: // REGRESANDO
                // Vuelve a su posicion inicial
                elegida = elegirDireccionHacia(posicionInicial, opciones);
                break;
        }

        direccion = elegida;
        posicion = posicion.siguiente(direccion);
    }

    /*
     Pasa a ASUSTADO durante 'duracion' movimientos.
     */
    public void asustar(int duracion) {
        this.estado = EstadoFantasma.ASUSTADO;
        this.tiempoAsustado = duracion;
    }

    public boolean estaAsustado() {
        return estado == EstadoFantasma.ASUSTADO;
    }

    /*
     * Solo es peligroso (mata a PacMan) cuando esta NORMAL.
     */
    public boolean esPeligroso() {
        return estado == EstadoFantasma.NORMAL;
    }

    /*
      Lo comio PacMan: pasa a REGRESANDO y vuelve a su posicion inicial.
      Se pone tiempoAsustado en 0 para que el temporizador no lo pase a NORMAL antes de tiempo
     */
    public void serComido() {
        this.estado = EstadoFantasma.REGRESANDO;
        this.tiempoAsustado = 0;
        volverAPosicionInicial();
    }

    /*
           Vuelve al estado normal (sin susto)
     */
    public void reiniciarEstado() {
        this.estado = EstadoFantasma.NORMAL;
        this.tiempoAsustado = 0;
    }

    /*
       Si queda tiempo de susto lo decrementa; al llegar a 0 vuelve a NORMAL
     */
    private void actualizarTemporizador() {
        if (tiempoAsustado > 0) {
            tiempoAsustado--;
            if (tiempoAsustado == 0) {
                estado = EstadoFantasma.NORMAL;
            }
        }
    }

    /*
      Entre las direcciones posibles, elige la que deja al fantasma mas cerca del destino
     */
    private Direccion elegirDireccionHacia(Posicion destino, List<Direccion> opciones) {
        if (opciones.isEmpty()) {
            return direccion; // sin opciones, mantiene la direccion actual
        }
        Direccion mejor = opciones.get(0);
        double mejorDistancia = posicion.siguiente(mejor).distanciaA(destino);
        for (Direccion d : opciones) {
            double distancia = posicion.siguiente(d).distanciaA(destino);
            if (distancia < mejorDistancia) {
                mejorDistancia = distancia;
                mejor = d;
            }
        }
        return mejor;
    }

    /*
            elige una direccion al azar entre las posibles
     */
    private Direccion elegirDireccionAleatoria(List<Direccion> opciones) {
        if (opciones.isEmpty()) {
            return direccion;
        }
        return opciones.get(random.nextInt(opciones.size()));
    }
}