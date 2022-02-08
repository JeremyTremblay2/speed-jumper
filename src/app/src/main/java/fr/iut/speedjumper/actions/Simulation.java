package fr.iut.speedjumper.actions;

import fr.iut.speedjumper.entites.Entite;

/**
 * interface de la mise ajour de l'etat de jeu
 */
public interface Simulation {
    void miseAJourEtatDeJeu(Entite entite, double temps);
}
