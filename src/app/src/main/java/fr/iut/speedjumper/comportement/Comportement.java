package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.entites.Entite;

/**
 * Interface pour les comportements d'entite
 */
public interface Comportement {
    void agit(Entite entite, double temps);
}
