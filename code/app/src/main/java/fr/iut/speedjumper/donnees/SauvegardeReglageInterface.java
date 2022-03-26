package fr.iut.speedjumper.donnees;

import java.io.FileOutputStream;
import java.io.Serializable;

public interface SauvegardeReglageInterface {
    void save(FileOutputStream fichier, Serializable ASauvegarder);
}
