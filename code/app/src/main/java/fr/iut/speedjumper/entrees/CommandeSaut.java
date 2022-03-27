package fr.iut.speedjumper.entrees;

import fr.iut.speedjumper.actions.Sauteur;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.monde.Niveau;

/**
 * Classe permettant de gérer le saut
 */
public class CommandeSaut implements Commande {
    private Sauteur sauteur;

    public CommandeSaut(TableauJeu tableauJeu) throws IllegalArgumentException {
        sauteur = new Sauteur(tableauJeu);
    }

    /**
     * Met a jout l'entite effectuant le saut
     * @param entite entite faisant le saut
     * @param temps temps dans la boucle
     */
    @Override
    public void execute(Entite entite, double temps) {
        if (entite.isSurSol()) {
            sauteur.miseAJourEtatDeJeu(entite, temps);
            new Thread(sauteur).start();
        }
    }
}
