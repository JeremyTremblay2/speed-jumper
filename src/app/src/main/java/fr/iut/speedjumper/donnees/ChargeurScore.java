package fr.iut.speedjumper.donnees;

import java.util.List;

import fr.iut.speedjumper.logique.Score;

/**
 * interface pour charger les scores
 */
public interface ChargeurScore {
    List<List<Score>> charge(String chemin);
}
