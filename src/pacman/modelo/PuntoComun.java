package pacman.modelo;

// Punto comun vale 10 puntos, no tiene efecto especial
public class PuntoComun extends Punto {

    public PuntoComun() {
        this.valor = 10;
    }
    // No sobreescribe consumir() porque el del padre ya hace lo que necesita
}
