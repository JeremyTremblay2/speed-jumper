package fr.iut.speedjumper.actions.collisionneurs;

import android.util.Log;

import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.monde.Carte2D;

/**
 * classe permettant de gerer la collision de la carte
 */
public class CollisionneurCarte {
    private final CollisionneurAABB collisionneur;

    /**
     * constructeur de la classe
     */
    public CollisionneurCarte() {
        collisionneur = new CollisionneurAABB();
    }

    /**
     * methode permettant de verifier la collision
     * @param collision
     * @param carte
     * @return
     */
    public boolean collisionne(Rectangle collision, Carte2D carte) {
        if (collision == null || carte == null || carte.getLesTuiles() == null) {
            return false;
        }

        double largeurTuile = carte.getDimensionTuiles().getLargeur();
        double hauteurTuile = carte.getDimensionTuiles().getHauteur();

        int coinGauche = (int) (collision.getPosition().getX() / largeurTuile);
        int coinSuperieur = (int) (collision.getPosition().getY() / hauteurTuile);
        int coinDroite = (int) ((collision.getPosition().getX() + collision.getDimension().getLargeur())
                / carte.getDimensionTuiles().getLargeur());
        int coinInferieur = (int) ((collision.getPosition().getY() + collision.getDimension().getLargeur())
                / carte.getDimensionTuiles().getHauteur());

        Rectangle collisionTuileRelative, collisionTuileAbsolue;

        double largeurCarte = carte.getDimensionCarte().getLargeur();
        double hauteurCarte = carte.getDimensionCarte().getHauteur();

        if (coinInferieur >= hauteurCarte || coinInferieur < 0
                || coinSuperieur >= hauteurCarte || coinSuperieur < 0
                || coinDroite >= largeurCarte || coinDroite < 0
                || coinGauche >= largeurCarte || coinGauche < 0) {
            return true;
        }

        for(int x = coinGauche; x <= coinDroite; x++) {
            for(int y = coinSuperieur; y <= coinInferieur; y++) {
                collisionTuileRelative = carte.getTuile(y, x).getCollision();
                if (collisionTuileRelative != null) {
                    collisionTuileAbsolue = new Rectangle(collisionTuileRelative.getPosition().getX() + x * largeurTuile,
                            collisionTuileRelative.getPosition().getY() + y * hauteurTuile,
                            collisionTuileRelative.getDimension());
                    if (collisionneur.collisionne(collision, collisionTuileAbsolue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
