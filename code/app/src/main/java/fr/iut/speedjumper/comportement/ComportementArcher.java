package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;

public class ComportementArcher extends Comportement {
    public ComportementArcher(TableauJeu tableauJeu) throws IllegalArgumentException {
        super(tableauJeu);
    }

    @Override
    public void agit(Entite entite, double temps) {

    }
}
