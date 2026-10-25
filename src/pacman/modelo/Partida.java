package pacman.modelo;

// La clase que une todo, maneja la logica de la partida
public class Partida {
    private int puntaje;
    private int vidas;
    private EstadoPartida estado;
    private Laberinto laberinto;
    private PacMan pacman;
    private Fantasma[] fantasmas;

    private static final int VIDAS_INICIALES = 3;
    private static final int DURACION_SUSTO = 20;  // turnos que dura el susto

    // Constructor, crea el laberinto, PacMan y 4 fantasmas
    public Partida() {
        this.laberinto = new Laberinto();
        this.pacman = new PacMan(new Posicion(15, 9));

        this.fantasmas = new Fantasma[4];
        fantasmas[0] = new Fantasma(new Posicion(8, 8));
        fantasmas[1] = new Fantasma(new Posicion(8, 9));
        fantasmas[2] = new Fantasma(new Posicion(8, 10));
        fantasmas[3] = new Fantasma(new Posicion(9, 9));

        // Arranca la partida al crear
        iniciar();
    }

    // Inicializa/reinicia la partida
    public void iniciar() {
        laberinto.cargarPuntos();
        pacman.volverAPosicionInicial();
        for (Fantasma f : fantasmas) {
            f.volverAPosicionInicial();
            f.reiniciarEstado();
        }
        puntaje = 0;
        vidas = VIDAS_INICIALES;
        estado = EstadoPartida.EN_CURSO;
    }

    // Mueve a PacMan en la dirección indicada
    public void moverPacMan(Direccion direccion) {
        if (estado != EstadoPartida.EN_CURSO) return;
        pacman.cambiarDireccion(direccion);
        pacman.mover(laberinto);
    }

    // Consume el punto en la posición de PacMan
    public void consumirPunto() {
        if (estado != EstadoPartida.EN_CURSO) return;
        Punto punto = laberinto.retirarPunto(pacman.getPosicion());
        if (punto != null) {
            punto.consumir(this);  // polimorfismo: PuntoComun o SuperPunto
        }
    }

    // Asusta a todos los fantasmas (llamado por SuperPunto)
    public void asustarFantasmas() {
        for (Fantasma f : fantasmas) {
            f.asustar(DURACION_SUSTO);
        }
    }

    // Mueve todos los fantasmas
    public void moverFantasmas() {
        if (estado != EstadoPartida.EN_CURSO) return;
        for (Fantasma f : fantasmas) {
            // Le dice al fantasma donde esta PacMan antes de moverlo
            f.setObjetivo(pacman.getPosicion());
            f.mover(laberinto);
        }
    }

    // Si PacMan colisiona con fantasma asustado, lo come
    public void comerFantasma() {
        if (estado != EstadoPartida.EN_CURSO) return;
        for (Fantasma f : fantasmas) {
            if (f.colisionaCon(pacman) && f.estaAsustado()) {
                f.serComido();
                sumarPuntaje(Fantasma.PUNTOS_AL_SER_COMIDO);
            }
        }
    }

    // Si PacMan colisiona con fantasma peligroso, pierde vida
    public void colisionarConFantasma() {
        if (estado != EstadoPartida.EN_CURSO) return;
        for (Fantasma f : fantasmas) {
            if (f.colisionaCon(pacman) && f.esPeligroso()) {
                perderVida();
                if (vidas > 0) {
                    reiniciarPosiciones();
                }
                return;  // solo pierde una vida por colisión
            }
        }
    }

    // Si no quedan vidas, pierde
    public void verificarDerrota() {
        if (estado == EstadoPartida.EN_CURSO && vidas == 0) {
            estado = EstadoPartida.DERROTA;
        }
    }

    // Si no quedan puntos, gana
    public void verificarVictoria() {
        if (estado == EstadoPartida.EN_CURSO && !laberinto.quedanPuntos()) {
            estado = EstadoPartida.VICTORIA;
        }
    }

    // Pausa la partida
    public void pausar() {
        if (estado == EstadoPartida.EN_CURSO) {
            estado = EstadoPartida.PAUSADA;
        }
    }

    // Reanuda la partida
    public void reanudar() {
        if (estado == EstadoPartida.PAUSADA) {
            estado = EstadoPartida.EN_CURSO;
        }
    }

    // Reinicia la partida si terminó
    public void reiniciarPartida() {
        if (estaFinalizada()) {
            iniciar();
        }
    }

    // Suma puntos al puntaje
    public void sumarPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    // Verifica si la partida termina
    public boolean estaFinalizada() {
        return estado == EstadoPartida.VICTORIA || estado == EstadoPartida.DERROTA;
    }

    // Getters para consultar estado
    public int getPuntaje() { return puntaje; }
    public int getVidas() { return vidas; }
    public EstadoPartida getEstado() { return estado; }

    // Pierde una vida
    private void perderVida() {
        vidas--;
    }

    // Vuelve a todos a su posicion inicial (despues de perder vida)
    private void reiniciarPosiciones() {
        pacman.volverAPosicionInicial();
        for (Fantasma f : fantasmas) {
            f.volverAPosicionInicial();
        }
    }
}
