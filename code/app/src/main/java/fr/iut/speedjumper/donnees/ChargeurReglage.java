package fr.iut.speedjumper.donnees;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Classe permettant de charger depuis un fichier les reglages
 */

public class ChargeurReglage implements ChargeurReglageInterface {

    /**
     * methode permettant de charger les reglage depuis le fichier fourni en entre
     * @param fichier
     * @return un serializable avec les deux valeurs pour le reglages
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    @Nullable
    @Override
    public Serializable load(FileInputStream fichier){
        Serializable retour = null;
        try (ObjectInputStream Object = new ObjectInputStream(fichier)) {
            retour = (Serializable) Object.readObject();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return retour;
    }

}
