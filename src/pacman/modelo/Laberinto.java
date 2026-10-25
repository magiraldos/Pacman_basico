package pacman.modelo;

import java.util.ArrayList;
import java.util.List;

// El tablero del juego, grilla de celdas
public class Laberinto {
    private Celda[][] celdas;  // matriz de celdas
    private int filas;
    private int columnas;

    // Mapa hardcodeado, 1 = pared, 0 = camino, 2 = súper punto
    private static final int[][] MAPA = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
            {1,2,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,2,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1},
            {1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
            {1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1},
            {1,1,1,1,0,1,0,0,0,0,0,0,0,1,0,1,1,1,1},
            {1,1,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1,1,1},
            {0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
            {1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1},
            {1,1,1,1,0,1,0,0,0,0,0,0,0,1,0,1,1,1,1},
            {1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,1},
            {1,2,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,2,1},
            {1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1},
            {1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
            {1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    // Constructor, arma las celdas a partir del mapa
    public Laberinto() {
        this.filas = MAPA.length;
        this.columnas = MAPA[0].length;
        this.celdas = new Celda[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                boolean esPared = (MAPA[f][c] == 1);
                celdas[f][c] = new Celda(esPared);
            }
        }
    }

    // Coloca puntos en todas las celdas transitables
    public void cargarPuntos() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (celdas[f][c].esTransitable()) {
                    if (MAPA[f][c] == 2) {
                        celdas[f][c].colocarPunto(new SuperPunto());
                    } else {
                        celdas[f][c].colocarPunto(new PuntoComun());
                    }
                }
            }
        }
    }

    // Devuelve la celda en una posicion
    public Celda getCelda(Posicion posicion) {
        return celdas[posicion.getFila()][posicion.getColumna()];
    }

    // Verifica si una posicion es transitable
    public boolean esTransitable(Posicion posicion) {
        int f = posicion.getFila();
        int c = posicion.getColumna();
        // Primero: ¿está dentro del tablero?
        if (f < 0 || f >= filas || c < 0 || c >= columnas) {
            return false;
        }
        // Segundo: ¿es camino (no pared)?
        return celdas[f][c].esTransitable();
    }

    // Devuelve las direcciones a las que se puede mover desde una posicion
    public List<Direccion> direccionesDisponibles(Posicion posicion) {
        List<Direccion> disponibles = new ArrayList<>();
        for (Direccion d : Direccion.values()) {
            if (esTransitable(posicion.siguiente(d))) {
                disponibles.add(d);
            }
        }
        return disponibles;
    }

    // Retira el punto de una celda y lo devuelve (null si no habia punto)
    public Punto retirarPunto(Posicion posicion) {
        Celda celda = getCelda(posicion);
        if (celda.tienePunto()) {
            return celda.retirarPunto();
        }
        return null;
    }

    // Verifica si quedan puntos en el laberinto (para saber si gana)
    public boolean quedanPuntos() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (celdas[f][c].tienePunto()) {
                    return true;
                }
            }
        }
        return false;
    }
}
