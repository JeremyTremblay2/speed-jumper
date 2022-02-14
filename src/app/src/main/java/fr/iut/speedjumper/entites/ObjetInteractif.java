package fr.iut.speedjumper.entites;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisions;
import fr.iut.speedjumper.comportement.Comportement;
import fr.iut.speedjumper.comportement.ComportementNull;
import fr.iut.speedjumper.effets.Effet;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;

public class ObjetInteractif extends Entite {
    private Comportement comportement;
    private List<Effet> lesEffets;

    public ObjetInteractif(Position2D position, Rectangle collision, Dimension dimension,
                           double velocite, Comportement comportement, List<Effet> lesEffets)
            throws IllegalArgumentException {
        super(position, collision, dimension, velocite);
        this.comportement = comportement;
        this.lesEffets = lesEffets == null ? new ArrayList<Effet>() : lesEffets;
    }

    public Comportement getComportement() {
        return comportement;
    }

    public List<Effet> getLesEffets() {
        return lesEffets;
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

    public void appliquerEffets(PersonnageJouable joueur) {
        for (Effet effet : lesEffets) {
            effet.appliquerEffet(joueur);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjetInteractif objet = (ObjetInteractif) o;
        return equals(objet);
    }

    public boolean equals(ObjetInteractif objet) {
        return super.equals(objet)
                && comportement.equals(objet.getComportement())
                && lesEffets.equals(objet.getLesEffets());
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + super.hashCode();
        resultat = premier * resultat + ((comportement == null) ? 0 : comportement.hashCode());
        resultat = premier * resultat + ((lesEffets == null) ? 0 : lesEffets.hashCode());
        return resultat;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder(super.toString());
        chaine.append("\nComportement : ");
        chaine.append(comportement.toString());
        chaine.append("\nLes effets :");
        for (Effet effet : lesEffets) {
            chaine.append("\n-");
            chaine.append(effet.toString());
        }
        return chaine.toString();
    }
}
