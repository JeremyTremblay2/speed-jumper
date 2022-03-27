package fr.iut.speedjumper.donnees;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe permet de sauvegarder dans un fichier les réglages pour le volume enregistre
 */
public class SauvegardeReglage implements SauvegardeReglageInterface {

    /**
     * methode permettant d ecrire dans le fichier les données a sauvegarder
     * @param fichier
     * @param ASauvegarder
     * @throws FileNotFoundException
     */
    @Override
    public void save(FileOutputStream fichier, Serializable ASauvegarder) {
        try ( ObjectOutputStream fic = new ObjectOutputStream(fichier)){
            fic.writeObject(ASauvegarder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
