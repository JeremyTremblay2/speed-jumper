package fr.iut.speedjumper.actions.deplaceurs;

import fr.iut.speedjumper.actions.Simulation;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.monde.Carte2D;

/**
 * Classe permettant d'adapter le deplacement
 */
public class AdaptateurDeplaceur implements Simulation {
    private Deplaceur deplaceur;
    private Direction direction;
    private TableauJeu tableauJeu;

    /**
     * constructeur de la classe
     * @param direction direction dans laquelle se deplacer
     * @param carteCourante carte ou se deplacer
     */
    public AdaptateurDeplaceur(Direction direction, TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas "
                    + "être null.");
        }
        this.direction = direction;
        this.tableauJeu = tableauJeu;
        deplaceur = new Deplaceur(tableauJeu);
    }

    /**
     * retourne la direction
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * set la direction
     * @param direction la direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * met a jout l'etat de jeu en fonction du deplacement
     * @param entite entite qui se deplace
     * @param temps temps de la boucle
     */
    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        deplaceur.deplace(entite, temps, direction);
    }
}
