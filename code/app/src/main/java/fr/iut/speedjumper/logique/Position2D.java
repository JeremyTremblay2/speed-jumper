package fr.iut.speedjumper.logique;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Classe permettant de créer une position en 2D
 */
public class Position2D {
    private double x;
    private double y;

    /**
     * Constructeur de la classe Position2D
     * @param x coordonée X
     * @param y coordonée Y
     */
    public Position2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void ajouterPosition(Position2D position) {
        x += position.x;
        y += position.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position2D position = (Position2D) o;
        return equals(position);
    }

    public boolean equals(Position2D position) {
        return Double.compare(position.getX(), this.getX()) == 0
                && Double.compare(position.getY(), this.getY()) == 0;
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + Double.valueOf(x).hashCode();
        resultat = premier * resultat + Double.valueOf(y).hashCode();
        return resultat;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}
