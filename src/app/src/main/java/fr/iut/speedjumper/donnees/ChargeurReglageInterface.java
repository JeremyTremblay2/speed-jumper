package fr.iut.speedjumper.donnees;

import java.io.FileInputStream;

public interface ChargeurReglageInterface {

    Object load(FileInputStream fichier);
}
