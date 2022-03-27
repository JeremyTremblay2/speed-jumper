package fr.iut.speedjumper.donnees;

import java.io.FileInputStream;

/**
 * interface pour charger les reglages
 */

public interface ChargeurReglageInterface {

    Object load(FileInputStream fichier);
}
