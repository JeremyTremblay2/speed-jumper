package fr.iut.speedjumper.donnees;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ChargeurReglage implements ChargeurReglageInterface {
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
