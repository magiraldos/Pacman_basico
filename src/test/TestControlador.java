package test;

import pacman.controlador.ControladorJuego;
import pacman.modelo.Direccion;

public class TestControlador {

    public static void main(String[] args) {

        // RF01: Iniciar partida
        System.out.println("RF01: Iniciar partida");
        ControladorJuego.getInstance().iniciarPartida();
        System.out.println("Puntaje: " + ControladorJuego.getInstance().getPuntaje());
        System.out.println("Vidas: " + ControladorJuego.getInstance().getVidas());
        System.out.println("Estado: " + ControladorJuego.getInstance().getEstado());
        System.out.println();

        // RF02: Mover PacMan
        System.out.println("RF02: Mover PacMan");
        ControladorJuego.getInstance().moverPacMan(Direccion.IZQUIERDA);
        System.out.println("PacMan se movio a la izquierda");
        System.out.println("Estado: " + ControladorJuego.getInstance().getEstado());
        System.out.println();

        // RF03: Comer punto comun
        System.out.println("RF03: Comer punto comun");
        int puntajeAntes = ControladorJuego.getInstance().getPuntaje();
        ControladorJuego.getInstance().consumirPunto();
        int puntajeDespues = ControladorJuego.getInstance().getPuntaje();
        System.out.println("Puntaje antes: " + puntajeAntes);
        System.out.println("Puntaje despues: " + puntajeDespues);
        System.out.println();

        // RF05: Mover fantasmas
        System.out.println("RF05: Mover fantasmas");
        ControladorJuego.getInstance().moverFantasmas();
        System.out.println("Fantasmas movidos");
        System.out.println();

        // RF06: Comer fantasma asustado
        System.out.println("RF06: Comer fantasma asustado");
        puntajeAntes = ControladorJuego.getInstance().getPuntaje();
        ControladorJuego.getInstance().comerFantasma();
        puntajeDespues = ControladorJuego.getInstance().getPuntaje();
        System.out.println("Puntaje antes: " + puntajeAntes);
        System.out.println("Puntaje despues: " + puntajeDespues);
        System.out.println();

        // RF07: Colision con fantasma
        System.out.println("RF07: Colision con fantasma");
        int vidasAntes = ControladorJuego.getInstance().getVidas();
        ControladorJuego.getInstance().colisionarConFantasma();
        int vidasDespues = ControladorJuego.getInstance().getVidas();
        System.out.println("Vidas antes: " + vidasAntes);
        System.out.println("Vidas despues: " + vidasDespues);
        System.out.println();

        // RF08: Verificar derrota
        System.out.println("RF08: Verificar derrota");
        ControladorJuego.getInstance().verificarDerrota();
        System.out.println("Estado: " + ControladorJuego.getInstance().getEstado());
        System.out.println();

        // RF09: Verificar victoria
        System.out.println("RF09: Verificar victoria");
        ControladorJuego.getInstance().verificarVictoria();
        System.out.println("Estado: " + ControladorJuego.getInstance().getEstado());
        System.out.println();

        // RF10: Pausar y reanudar
        System.out.println("RF10: Pausar y reanudar");
        ControladorJuego.getInstance().pausar();
        System.out.println("Estado despues de pausar: " + ControladorJuego.getInstance().getEstado());
        ControladorJuego.getInstance().reanudar();
        System.out.println("Estado despues de reanudar: " + ControladorJuego.getInstance().getEstado());
        System.out.println();

        // RF11: Reiniciar partida
        System.out.println("RF11: Reiniciar partida");
        System.out.println("Estado antes: " + ControladorJuego.getInstance().getEstado());
        // reiniciarPartida() solo funciona si la partida termino, asi que no va a reiniciar aca
        ControladorJuego.getInstance().reiniciarPartida();
        System.out.println("Estado despues: " + ControladorJuego.getInstance().getEstado());
        System.out.println();

        // RF12: Consultar estado
        System.out.println("RF12: Consultar estado");
        System.out.println("Puntaje: " + ControladorJuego.getInstance().getPuntaje());
        System.out.println("Vidas: " + ControladorJuego.getInstance().getVidas());
        System.out.println("Estado: " + ControladorJuego.getInstance().getEstado());

        System.out.println();
        System.out.println("Todos los tests ejecutados");
    }
}