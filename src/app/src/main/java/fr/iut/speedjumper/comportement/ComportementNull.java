package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.entites.Entite;

/**
 * classe permettant de gerer le comportement null
 */
public class ComportementNull implements Comportement {
    @Override
    public void agit(Entite entite, double temps) {
        // Ne fait rien.
    }
}
