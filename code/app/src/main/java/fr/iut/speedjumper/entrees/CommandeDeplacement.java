package fr.iut.speedjumper.entrees;

import fr.iut.speedjumper.actions.deplaceurs.AdaptateurDeplaceur;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.monde.Niveau;

public class CommandeDeplacement implements Commande {
    private AdaptateurDeplaceur deplaceur;

    public CommandeDeplacement(Direction direction, TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas être null.");
        }
        deplaceur = new AdaptateurDeplaceur(direction, tableauJeu);
    }

    @Override
    public void execute(Entite entite, double temps) {
        deplaceur.miseAJourEtatDeJeu(entite, temps);
    }
}
