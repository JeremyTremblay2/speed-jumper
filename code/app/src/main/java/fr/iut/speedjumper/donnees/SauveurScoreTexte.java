package fr.iut.speedjumper.donnees;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import fr.iut.speedjumper.logique.Score;
import fr.iut.speedjumper.monde.Niveau;

/**
 * classe pour sauvegarder les scores
 */
public class SauveurScoreTexte implements SauveurScore {

    /**
     * methode pour sauvegarder les scores de chaques niveaux
     * @param lesNiveaux liste des niveaux a sauvegarder
     * @param chemin chemin du fichier ou sauvegarder
     */
    @Override
    public void sauvegarde(List<Niveau> lesNiveaux, String chemin) {
        try (FileWriter ecriveur = new FileWriter(chemin)) {
            BufferedWriter buffer = new BufferedWriter(ecriveur);
            for(Niveau niveau : lesNiveaux){
                buffer.write("N : " + niveau.getNumeroNiveau());
                for(Score score : niveau.getLesScores()){
                    buffer.write(score.getPseudo() + " : " + score.getScore());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
