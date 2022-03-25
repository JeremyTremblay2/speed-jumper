package fr.iut.speedjumper.donnees;

import java.io.InputStream;
import java.util.List;

import fr.iut.speedjumper.entites.Entite;

/**
 * interface pour charger les enemis
 */
public interface ChargeurEnnemis {
    List<List<Entite>> charge(InputStream fluxEntree);
}
