package fr.iut.speedjumper.effets;

import fr.iut.speedjumper.entites.PersonnageJouable;

public class EffetModificationScore implements Effet {
    private int valeur;

    public EffetModificationScore(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public void appliquerEffet(PersonnageJouable joueur) throws IllegalArgumentException {
        if (joueur == null) {
            throw new IllegalArgumentException("Le joueur passé en paramètre est null.");
        }
        joueur.getScore().augmenterScore(valeur);
    }
}
