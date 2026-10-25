package pacman.modelo;

import java.util.Objects;

// Representa ubicacion en el tablero
public class Posicion {
    private int fila;
    private int columna;

    // Constructor: crea una posicion con fila y columna
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna= columna;
    }

    // Devuelve la posicion vecina en la direccion indicada
    // No modifica la posicion actual, crea una nueva
    public Posicion siguiente(Direccion direccion) {
        switch (direccion) {
            case ARRIBA:    return new Posicion(fila - 1, columna);
            case ABAJO:     return new Posicion(fila + 1, columna);
            case IZQUIERDA: return new Posicion(fila, columna - 1);
            case DERECHA:   return new Posicion(fila, columna + 1);
            default:        return new Posicion(fila, columna);
        }
    }

    // Distancia Manhattan
    public int distanciaA(Posicion otra) {
        return Math.abs(this.fila - otra.fila) + Math.abs(this.columna - otra.columna);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    // Compara por valor (fila y columna), no por referencia en memoria
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posicion otra = (Posicion) obj;
        return fila == otra.fila && columna == otra.columna;
    }

    // Sobreescribir obligatoriamente el Hash debido a que sobreescribimos equals
    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}

