package fr.iut.speedjumper.actions;

import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;

public class CollisionneurPointRectangle {

    public boolean collisionne(Position2D position, Rectangle rectangle) {
        if (position == null || rectangle == null) {
            return false;
        }

        return position.getX() >= rectangle.getPosition().getX()
                && position.getY() >= rectangle.getPosition().getY()
                && position.getX() <= rectangle.getPosition().getX() + rectangle.getDimension().getLargeur()
                && position.getY() <= rectangle.getPosition().getY() + rectangle.getDimension().getHauteur();
    }
}
