package fr.iut.speedjumper.effets;

import fr.iut.speedjumper.entites.PersonnageJouable;

public class EffetChangementVie implements Effet {
    private int valeur;

    public EffetChangementVie(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public void appliquerEffet(PersonnageJouable joueur) throws IllegalArgumentException {
        if (joueur == null) {
            throw new IllegalArgumentException("Le joueur passé en paramètre est null.");
        }
        int pointsDeVieRestants = joueur.getPointsDeVie() - valeur;
        if (pointsDeVieRestants < 0) {
            pointsDeVieRestants = 0;
        }
        joueur.setPointsDeVie(pointsDeVieRestants);
    }
}
