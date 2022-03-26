package fr.iut.speedjumper.donnees;

import java.util.List;

import fr.iut.speedjumper.monde.Niveau;

/**
 * Interface permettant de sauvegarder les scores
 */
public interface SauveurScore {
    /**
     * Methode pour sauvegarder
     * @param lesNiveaux liste des niveaux a sauvegarder
     * @param chemin chemin du fichier ou sauvegarder
     */
    void sauvegarde(List<Niveau> lesNiveaux, String chemin);
}
