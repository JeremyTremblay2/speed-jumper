package fr.iut.speedjumper.actions.deplaceurs;

import android.util.Log;

import fr.iut.speedjumper.actions.collisionneurs.CollisionneurCarte;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.BoucleDeJeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.monde.Carte2D;

/**
 * Classe permettant de gerer l'action deplacement
 */
public class Deplaceur {
    private static final float VELOCITE_DANS_LES_AIRS = 1.4f;
    private static final double NOMBRE_PIXEL_VERIFICATION_VIDE = 4;
    private final double decalagePixelParMouvement;
    private CollisionneurCarte collisionneur;
    private TableauJeu tableauJeu;

    public Deplaceur(TableauJeu tableauJeu) throws IllegalArgumentException {
        this.tableauJeu = tableauJeu;
        collisionneur = new CollisionneurCarte();
        decalagePixelParMouvement = tableauJeu.getNiveauCourant().getCarte().getDimensionTuiles().getLargeur() * 0.1;
    }

    /**
     * methode permettant le deplacement d'une entite dans une certaine direction
     * @param entite entite faisant l'action
     * @param temps
     * @param direction direction dans laquelle deplacée l'entité
     * @throws IllegalArgumentException
     */
    public void deplace(Entite entite, double temps, Direction direction) throws IllegalArgumentException {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité fournie en paramètre du déplaceur est nulle.");
        }
        if (direction == null) {
            throw new IllegalArgumentException("La direction fournie en paramètre du déplaceur est nulle.");
        }

        Position2D nouvellePosition = entite.getPosition();
        Carte2D carteCourante = tableauJeu.getNiveauCourant().getCarte();
        double increment;

        if (entite.isSurSol()) {
            increment = entite.getVelocite() * (temps / BoucleDeJeu.TEMPS_AVANT_NOTIFICATION);
        }
        else {
            increment = entite.getVelocite() * (temps / BoucleDeJeu.TEMPS_AVANT_NOTIFICATION) / VELOCITE_DANS_LES_AIRS;
        }

        switch (direction) {
            case DROITE:
                nouvellePosition = new Position2D(entite.getPosition().getX() + increment, entite.getPosition().getY());
                break;
            case GAUCHE:
                nouvellePosition = new Position2D(entite.getPosition().getX() - increment, entite.getPosition().getY());
                break;
            case HAUT:
                nouvellePosition = new Position2D(entite.getPosition().getX(), entite.getPosition().getY() - increment);
                break;
            case BAS:
                nouvellePosition = new Position2D(entite.getPosition().getX(), entite.getPosition().getY() + increment);
                break;
        }

        Rectangle nouvelleCollision = new Rectangle(nouvellePosition.getX(),
                nouvellePosition.getY(),
                entite.getCollision().getDimension());

        Position2D nouvellePositionSuperieure = new Position2D(nouvellePosition.getX(),
                nouvellePosition.getY() + decalagePixelParMouvement);

        Rectangle nouvelleCollisionSuperieure = new Rectangle(nouvellePositionSuperieure.getX(),
                nouvellePositionSuperieure.getY(),
                entite.getCollision().getDimension());

        if (!collisionneur.collisionne(nouvelleCollision, carteCourante)) {
            entite.setPosition(nouvellePosition);
            detectionVide(entite);
        }
        else if (!collisionneur.collisionne(nouvelleCollisionSuperieure, carteCourante)) {
            entite.setPosition(nouvellePositionSuperieure);
            detectionVide(entite);
        }
    }

    private void detectionVide(Entite entite) {
        Position2D positionBas = new Position2D(entite.getPosition().getX(),
                entite.getPosition().getY() + NOMBRE_PIXEL_VERIFICATION_VIDE);

        Rectangle nouvelleCollision = new Rectangle(positionBas, entite.getCollision().getDimension());

        if (!collisionneur.collisionne(nouvelleCollision, tableauJeu.getNiveauCourant().getCarte())) {
            entite.setSurSol(false);
            entite.setChute(true);
        }
    }
}
