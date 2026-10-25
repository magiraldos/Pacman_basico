package pacman.controlador;

import pacman.modelo.Direccion;
import pacman.modelo.EstadoPartida;
import pacman.modelo.Partida;


public class ControladorJuego {

    // Unica instancia del controlador (Singleton)
    private static ControladorJuego instancia;

    // Partida actual (null si todavia no se inicio)
    private Partida partida;

    // Constructor privado- nadie mas puede crear controladores
    private ControladorJuego() {
    }

    /*
      Devuelve la unica instancia, creandola la primera vez
     */
    public static ControladorJuego getInstance() {
        if (instancia == null)
            instancia = new ControladorJuego();
        return instancia;
    }

    /** Crea una partida nueva */
    public void iniciarPartida() {
        partida = new Partida();
    }

    public void moverPacMan(Direccion direccion) {
        verificarPartidaIniciada();
        partida.moverPacMan(direccion);
    }

    public void consumirPunto() {
        verificarPartidaIniciada();
        partida.consumirPunto();
    }

    public void moverFantasmas() {
        verificarPartidaIniciada();
        partida.moverFantasmas();
    }

    public void comerFantasma() {
        verificarPartidaIniciada();
        partida.comerFantasma();
    }

    public void colisionarConFantasma() {
        verificarPartidaIniciada();
        partida.colisionarConFantasma();
    }

    public void verificarDerrota() {
        verificarPartidaIniciada();
        partida.verificarDerrota();
    }

    public void verificarVictoria() {
        verificarPartidaIniciada();
        partida.verificarVictoria();
    }

    public void pausar() {
        verificarPartidaIniciada();
        partida.pausar();
    }

    public void reanudar() {
        verificarPartidaIniciada();
        partida.reanudar();
    }

    public void reiniciarPartida() {
        verificarPartidaIniciada();
        partida.reiniciarPartida();
    }

    public int getPuntaje() {
        verificarPartidaIniciada();
        return partida.getPuntaje();
    }

    public int getVidas() {
        verificarPartidaIniciada();
        return partida.getVidas();
    }

    public EstadoPartida getEstado() {
        verificarPartidaIniciada();
        return partida.getEstado();
    }

    /** evita NullPointerException si se usa el controlador sin llamar antes a iniciarPartida(). */
    private void verificarPartidaIniciada() {
        if (partida == null)
            throw new IllegalStateException("La partida no fue iniciada. Llamar a iniciarPartida() primero.");
    }
}