package fr.iut.speedjumper.entites;

import androidx.annotation.NonNull;

import java.util.Objects;

import fr.iut.speedjumper.comportement.Comportement;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;

/**
 * Classe abstraite pour les entite vivante
 */
public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_PAR_DEFAUT = 10;
    private final int pointsDeViesInitiaux;
    private int pointsDeVie;
    private int degats;

    public Vivant(Position2D position, Rectangle collision, Dimension dimension, double velocite,
                  int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, velocite);
        this.pointsDeVie = pointsDeVie <= 0 ? POINTS_DE_VIE_PAR_DEFAUT : pointsDeVie;
        pointsDeViesInitiaux = pointsDeVie;
        this.degats = degats;
    }

    public Vivant(Position2D position, Rectangle collision, Dimension dimension, double velocite,
                  int degats) throws IllegalArgumentException {
        this(position, collision, dimension, velocite, degats, POINTS_DE_VIE_PAR_DEFAUT);
    }

    /**
     * retourne les points de vie
     * @return
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * set les points de vies
     * @param pointsDeVie nouveau nombre de pv
     */
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    /**
     * retourne le nombre de degat
     * @return
     */
    public int getDegats() {
        return degats;
    }

    /**
     * retourne le nombre de points de vie initial
     * @return
     */
    public int getPointsDeViesInitiaux() {
        return pointsDeViesInitiaux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vivant vivant = (Vivant) o;
        return equals(vivant);
    }

    public boolean equals(Vivant vivant) {
        return super.equals(vivant)
                && degats == vivant.getDegats()
                && pointsDeViesInitiaux == vivant.getPointsDeViesInitiaux();
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + super.hashCode();
        resultat = premier * resultat + degats ^ (degats >>> 16);
        resultat = premier * resultat + pointsDeViesInitiaux ^ (pointsDeViesInitiaux >>> 16);
        return resultat;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString()
                + "\n" + pointsDeVie + "\u2764"
                + "\n" + degats + "\u2764 degats";
    }
}
