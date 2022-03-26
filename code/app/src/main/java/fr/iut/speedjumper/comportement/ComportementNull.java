package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;

/**
 * classe permettant de gerer le comportement null
 */
public class ComportementNull extends Comportement {
    public ComportementNull(TableauJeu tableauJeu) throws IllegalArgumentException {
        super(tableauJeu);
    }

    @Override
    public void agit(Entite entite, double temps) {
        // Ne fait rien.
    }
}
