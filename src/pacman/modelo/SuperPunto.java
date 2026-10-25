package pacman.modelo;

// Súper punto: vale 50 puntos y asusta a los fantasmas
public class SuperPunto extends Punto {

    public SuperPunto() {
        this.valor = 50;
    }

    // Sobreescribe consumir() para agregar el efecto de asustar fantasmas
    @Override
    public void consumir(Partida partida) {
        super.consumir(partida);         // primero suma puntaje (lo del padre)
        partida.asustarFantasmas();      // después asusta a los fantasmas
    }
}