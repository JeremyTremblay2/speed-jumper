package fr.iut.speedjumper.donnees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.logique.Score;

/**
 * Classe permettant de charger les scores sauvegarde
 */
public class ChargeurScoreTextuel implements ChargeurScore {
    private static final String SEPARATEUR = ":";
    private static final String DEBUT_NUMERO_NIVEAU = "#";

    /**
     * methode permettant de charger les scores
     * @return
     */
    @Override
    public List<List<Score>> charge(InputStream fluxEntree) {
        List<List<Score>> lesScores = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(fluxEntree))) {
            String ligne;
            List<Score> scores = new ArrayList<>();

            while ((ligne = lecteur.readLine()) != null) {
                String[] score = ligne.split(SEPARATEUR);

                if (score[0].trim().equals(DEBUT_NUMERO_NIVEAU)) {
                    lesScores.add(scores);
                    scores = new ArrayList<>();
                }
                else if (score.length == 2) {
                    scores.add(new Score(score[0], Integer.parseInt(score[1].trim())));
                }
            }
            lesScores.remove(0);
            lesScores.add(scores);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lesScores;
    }
}