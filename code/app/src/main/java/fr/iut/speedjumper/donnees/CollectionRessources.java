package fr.iut.speedjumper.donnees;

import android.content.Context;

import java.io.InputStream;
import java.util.*;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;

/**
 * Classe permettant de gerer les différentes collection de ressources
 */
public class CollectionRessources {
    private static CollectionRessources instance;
    private Context contexte;

    private List<InputStream> lesCartes;
    private List<InputStream> lesJeuxDeTuilesCollisions;
    private List<Position2D> lesPointsDepart;
    private List<Position2D> lesPointsArrivee;

    private InputStream fichierConfigurationTouches;
    private InputStream fichierScores;

    /**
     * constructeur de la classe initialisant les différents listes
     */
    public CollectionRessources(Context contexte) throws IllegalArgumentException {
        if (contexte == null) {
            throw new IllegalArgumentException("Le contexte passé en paramètre ne peut pas être null.");
        }
        this.contexte = contexte;
        lesCartes = new ArrayList<>();
        lesJeuxDeTuilesCollisions = new ArrayList<>();
        lesPointsDepart = new ArrayList<>();
        lesPointsArrivee = new ArrayList<>();
        ajouterDonnees();
        instance = this;
    }

    /**
     * methode retourant l'instance d'une collection de ressources
     * @return
     */
    public static CollectionRessources getInstance() {
        if (instance == null) {
            throw new IllegalStateException("La collection de ressource n'a pas encore été initialisée.");
        }
        return instance;
    }

    /**
     * retoune la liste des cartes
     * @return
     */
    public List<InputStream> getLesCartes() {
        return lesCartes;
    }

    /**
     * retounr le fichuier de config des touches
     * @return
     */
    public InputStream getFichierConfigurationTouches() {
        return fichierConfigurationTouches;
    }

    /**
     * retourne le chemin du fichier de score
     * @return
     */
    public InputStream getFichierScores() {
        return fichierScores;
    }

    /**
     * retourne la liste des collision des tuiles
     * @return
     */
    public List<InputStream> getLesJeuxDeTuilesCollisions() {
        return lesJeuxDeTuilesCollisions;
    }

    public List<Position2D> getLesPointsDepart() {
        return lesPointsDepart;
    }

    public List<Position2D> getLesPointsArrivee() {
        return lesPointsArrivee;
    }

    private void ajouterDonnees() {
        fichierConfigurationTouches = Objects.requireNonNull(contexte.getResources().openRawResource(R.raw.touches));
        fichierScores = Objects.requireNonNull(contexte.getResources().openRawResource(R.raw.scores));

        lesCartes.add(Objects.requireNonNull(contexte.getResources().openRawResource(R.raw.carte1)));
        lesCartes.add(Objects.requireNonNull(contexte.getResources().openRawResource(R.raw.carte2)));
        lesCartes.add(Objects.requireNonNull(contexte.getResources().openRawResource(R.raw.carte3)));
        lesCartes.add(Objects.requireNonNull(contexte.getResources().openRawResource(R.raw.carte4)));

        lesJeuxDeTuilesCollisions.add(contexte.getResources().openRawResource(R.raw.caverne_moussue));

        lesPointsArrivee.add(new Position2D(512, 320));
        lesPointsArrivee.add(new Position2D(128, 1792));
        lesPointsArrivee.add(new Position2D(128, 448));
        lesPointsArrivee.add(new Position2D(6336, 320));

        lesPointsDepart.add(new Position2D(128, 1664));
        lesPointsDepart.add(new Position2D(128, 128));
        lesPointsDepart.add(new Position2D(196, 5660));
        lesPointsDepart.add(new Position2D(256, 1344));
    }
}
