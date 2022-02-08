package fr.iut.speedjumper.entrees;

import fr.iut.speedjumper.actions.Sauteur;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.monde.Niveau;

/**
 * Classe permettant de gérer le saut
 */
public class CommandeSaut implements Commande {
    private Sauteur sauteur;

    /**
     * Constructeur de la classe
     * @param niveau niveau courrant
     * @throws IllegalArgumentException
     */
    public CommandeSaut(Niveau niveau) throws IllegalArgumentException {
        sauteur = new Sauteur(niveau.getCarte());
    }

    /**
     * Met a jout l'entite effectuant le saut
     * @param entite entite faisant le saut
     * @param temps temps dans la boucle
     */
    @Override
    public void execute(Entite entite, float temps) {
        sauteur.miseAJourEtatDeJeu(entite, temps);
        new Thread(sauteur).start();
    }
}
