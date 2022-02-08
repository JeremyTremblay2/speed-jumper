package fr.iut.speedjumper.logique;

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
        return 7 * Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}
