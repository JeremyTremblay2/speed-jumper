package fr.iut.speedjumper.donnees;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import fr.iut.speedjumper.monde.Tuile;
import fr.iut.speedjumper.utilitaire.InvalidFormatException;

/**
 * interface permettant de charger la liste de tuiles
 */
public interface ChargeurDeJeuxDeTuiles {
    List<Tuile> charge(InputStream fluxEntree) throws FileNotFoundException, ParseException, InvalidFormatException;
}
