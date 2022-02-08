package fr.iut.speedjumper.actions;

import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.monde.ArrierePlan;

/**
 * classe permettant d'animer le fond  et de le faire bouger
 */
public class DeplaceurArrierePlan {
    public void deplace(ArrierePlan arrierePlan, float temps, Direction direction) {
        float vitesse = arrierePlan.getVitesseDefilement();
        float deplacement = vitesse * (temps / 1000000000);
        Position2D nouvellePosition;
        switch (direction) {
            case DROITE:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX() + deplacement,
                        arrierePlan.getPosition().getY());
                break;
            case GAUCHE:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX() - deplacement,
                        arrierePlan.getPosition().getY());
                break;
            case HAUT:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX(),
                        arrierePlan.getPosition().getY() - deplacement);
                break;
            case BAS:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX() + deplacement,
                        arrierePlan.getPosition().getY() + deplacement);
                break;
            default:
                throw new IllegalArgumentException();
        }
        arrierePlan.setPosition(nouvellePosition);
    }
}
