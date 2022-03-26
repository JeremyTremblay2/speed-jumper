package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;

/**
 * Interface pour les comportements d'entite
 */
public abstract class Comportement {
    protected TableauJeu tableauJeu;

    public Comportement(TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas "
                    + "être null");
        }
        this.tableauJeu = tableauJeu;
    }

    public abstract void agit(Entite entite, double temps);
}
