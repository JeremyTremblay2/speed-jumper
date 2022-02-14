package fr.iut.speedjumper.comportement;

import java.util.List;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Position2D;

public class ComportementFantome extends Comportement {
    private static final int NOMBRE_MAXIMUM_POSITIONS = 500;
    private List<Position2D> lesPositions;
    private int nombrePositions;

    public ComportementFantome(TableauJeu tableauJeu, int nombrePositions)
            throws IllegalArgumentException {
        super(tableauJeu);
        if (nombrePositions <= 0 || nombrePositions > NOMBRE_MAXIMUM_POSITIONS) {
            nombrePositions = NOMBRE_MAXIMUM_POSITIONS;
        }
        this.nombrePositions = nombrePositions;
    }

    @Override
    public void agit(Entite entite, double temps) {
        lesPositions.add(tableauJeu.getJoueur().getPosition());
        if (lesPositions.size() > nombrePositions) {
            lesPositions.remove(nombrePositions);
        }
        entite.setPosition(lesPositions.get(0));
    }
}
