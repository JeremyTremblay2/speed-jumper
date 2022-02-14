package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;

public class ComportementMarcheEtTir extends Comportement {
    public ComportementMarcheEtTir(TableauJeu tableauJeu) throws IllegalArgumentException {
        super(tableauJeu);
    }

    @Override
    public void agit(Entite entite, double temps) {

    }
}
