package fr.iut.speedjumper.comportement;

import fr.iut.speedjumper.actions.deplaceurs.AdaptateurDeplaceur;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.logique.Position2D;

/**
 * classe gerant le comportement marche
 */
public class ComportementMarcheAvecChute extends Comportement {
    private AdaptateurDeplaceur deplaceur;
    private Position2D dernierePosition = null;

    public ComportementMarcheAvecChute(TableauJeu tableauJeu) throws IllegalArgumentException {
        super(tableauJeu);
    }

    @Override
    public void agit(Entite entite, double temps) {
        if (entite.getPosition() == dernierePosition) {
            inverseDirection(entite);
        }
        deplaceur = new AdaptateurDeplaceur(entite.getDirection(), tableauJeu);

        deplaceur.miseAJourEtatDeJeu(entite, temps);
        dernierePosition = entite.getPosition();
    }

    private void inverseDirection(Entite entite) {
        if (entite.getDirection() == Direction.DROITE) {
            entite.setDirection(Direction.GAUCHE);
            deplaceur.setDirection(Direction.GAUCHE);
        }
        else {
            entite.setDirection(Direction.DROITE);
            deplaceur.setDirection(Direction.DROITE);
        }
    }
}
