package fr.iut.speedjumper.donnees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.monde.Tuile;


/**
 * classe permettant de charger les tuiles depuis un fichier
 */
public class ChargeurDeJeuxDeTuilesTextuel implements ChargeurDeJeuxDeTuiles {
    private static final String DELIMITEUR = " ";
    private List<Tuile> lesTuiles;

    /**
     * constructeurt de la classe
     */
    public ChargeurDeJeuxDeTuilesTextuel() {
        lesTuiles = new ArrayList<>();
    }

    /**
     * Methode permettant de charger depuis un chemin la liste de tuiles
     * @return la liste de tuile
     */
    @Override
    public List<Tuile> charge(InputStream fluxEntree) {
        int nombreTuile = 0, indexTuile;

        try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(fluxEntree))) {
            String ligne;
            ligne = lecteur.readLine();
            String[] uneTuile = ligne.split(DELIMITEUR);
            Dimension dimension = new Dimension(Float.parseFloat(uneTuile[0].trim()),
                    Float.parseFloat(uneTuile[1].trim()));

            while ((ligne = lecteur.readLine()) != null) {
                uneTuile = ligne.split(DELIMITEUR);
                indexTuile = Integer.parseInt(uneTuile[0].trim());

                while (nombreTuile < indexTuile) {
                    lesTuiles.add(new Tuile(null, dimension));
                    nombreTuile++;
                }

                lesTuiles.add(new Tuile(
                        new Rectangle(Float.parseFloat(uneTuile[1].trim()),
                                Float.parseFloat(uneTuile[2].trim()),
                                Float.parseFloat(uneTuile[3].trim()),
                                Float.parseFloat(uneTuile[4].trim())),
                                dimension));
                nombreTuile++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return lesTuiles;
    }
}
