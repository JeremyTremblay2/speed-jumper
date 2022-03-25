package fr.iut.speedjumper.donnees;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SauvegardeReglage implements SauvegardeReglageInterface {
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
