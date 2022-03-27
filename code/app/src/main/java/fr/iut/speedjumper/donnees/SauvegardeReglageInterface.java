package fr.iut.speedjumper.donnees;

import java.io.FileOutputStream;
import java.io.Serializable;

/**
 * interface pour sauvegarder les reglages
 */

public interface SauvegardeReglageInterface {
    void save(FileOutputStream fichier, Serializable ASauvegarder);
}
