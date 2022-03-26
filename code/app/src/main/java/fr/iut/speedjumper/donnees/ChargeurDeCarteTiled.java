package fr.iut.speedjumper.donnees;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import fr.iut.speedjumper.monde.Carte2D;
import fr.iut.speedjumper.monde.Tuile;
import fr.iut.speedjumper.utilitaire.InvalidFormatException;

/**
 * interface permettant de charger les tuiles depuis un fichier CSV
 */
public interface ChargeurDeCarteTiled {
    Carte2D charge(InputStream fluxEntree, List<Tuile> lesTuiles) throws FileNotFoundException, ParseException, InvalidFormatException;
}
