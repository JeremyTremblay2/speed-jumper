package fr.iut.speedjumper.entites;

import androidx.annotation.NonNull;

import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisions;
import fr.iut.speedjumper.comportement.Comportement;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.logique.Score;

/**
 * Classe permettant de gerer les personnage jouable
 */
public class PersonnageJouable extends Vivant {
    private Score score;

    public PersonnageJouable(Position2D position, Rectangle collision, Dimension dimension,
                             double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, velocite, degats, pointsDeVie);
    }

    public PersonnageJouable(Position2D position, Rectangle collision, Dimension dimension,
                             double velocite, int degats) throws IllegalArgumentException {
        super(position, collision, dimension, velocite, degats);
    }

    public Score getScore() {
        return score;
    }

    @Override
    public void miseAJour(double temps) {
        //Ne fait rien ici.
    }

    @Override
    public void accepte(VisiteurCollisions visiteur, ObjetInteractif objet) {
        visiteur.visite(this, objet);
    }

    @Override
    public void accepte(VisiteurCollisions visiteur, PersonnageJouable joueur) {
        visiteur.visite(this, joueur);
    }

    @Override
    public void accepte(VisiteurCollisions visiteur, Ennemi ennemi) {
        visiteur.visite(this, ennemi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageJouable personnageJouable = (PersonnageJouable) o;
        return equals(personnageJouable);
    }

    public boolean equals(PersonnageJouable personnageJouable) {
        return super.equals(personnageJouable)
                && score.equals(personnageJouable.getScore());
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + super.hashCode();
        resultat = premier * resultat + ((score == null) ? 0 : score.hashCode());
        return resultat;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + ", " + score;
    }
}
