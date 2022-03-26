package fr.iut.speedjumper.entites;

import androidx.annotation.NonNull;

import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisions;
import fr.iut.speedjumper.comportement.Comportement;
import fr.iut.speedjumper.comportement.ComportementNull;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;

/**
 * Classe permettant de gerer les enemis
 */
public class Ennemi extends Vivant {
    private Comportement comportement;

    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, double velocite,
                  int degats, int pointsDeVie, Comportement comportement) throws IllegalArgumentException {
        super(position, collision, dimension, velocite, degats, pointsDeVie);
        this.comportement = comportement;
    }

    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, double velocite,
                  int degats, Comportement comportement) throws IllegalArgumentException {
        super(position, collision, dimension, velocite, degats);
        this.comportement = comportement;
    }

    public Comportement getComportement() {
        return comportement;
    }

    @Override
    public void miseAJour(double temps) {
        if (comportement != null) {
            comportement.agit(this, temps);
        }
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
        if (!super.equals(o)) return false;
        Ennemi ennemi = (Ennemi) o;
        return equals(ennemi);
    }

    public boolean equals(Ennemi ennemi) {
        return super.equals(ennemi)
                && comportement.equals(ennemi.getComportement());
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + super.hashCode();
        resultat = premier * resultat + ((comportement == null) ? 0 : comportement.hashCode());
        return resultat;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString()
                + "\n Comportement : " + comportement.toString();
    }
}
