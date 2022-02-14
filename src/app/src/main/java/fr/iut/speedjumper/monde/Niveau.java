package fr.iut.speedjumper.monde;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Score;
import fr.iut.speedjumper.observateurs.ObservateurEntite;
import fr.iut.speedjumper.observateurs.SujetEntite;
import fr.iut.speedjumper.observateurs.TypeNotification;

/**
 * Classe pour un niveau
 */
public class Niveau extends SujetEntite {
    private static final int NOMBRE_SCORE_MAXIMUM = 10;
    private static final int TEMPS_IMPARTI_PAR_DEFAUT = 300;
    private static int nombreNiveaux = 0;
    private final int numeroNiveau;
    private Carte2D carte;
    private List<ArrierePlan> lesArrieresPlans;
    private List<Entite> lesEntites;
    private Position2D pointDepart;
    private Position2D pointArrivee;
    private List<Score> lesScores;
    private int tempsImparti;

    /**
     * Constructeur de la classe niveau
     * @param carte carte du niveau
     * @param arrierePlans liste des images de l'arriere plan
     * @param lesEntites liste des entitées présente sur le niveau
     * @param lesScores liste des scores réalisés sur le niveau
     * @param pointDepart coordonées du point de départ
     * @throws IllegalArgumentException
     */
    public Niveau(Carte2D carte, List<ArrierePlan> arrierePlans, List<Entite> lesEntites, List<Score> lesScores,
                  Position2D pointDepart, Position2D pointsArrivee, int tempsImparti) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte est requise lors de la création d'un niveau "
                    + "et ne peut être nulle.");
        }
        if (pointDepart == null || pointsArrivee == null) {
            throw new IllegalArgumentException("La position de départ et d'arrivée est requise lors de la création d'un "
                    + "niveau et ne peut être nulle.");
        }

        this.carte = carte;
        this.lesScores = lesScores == null ? new ArrayList<>() : lesScores;
        this.lesArrieresPlans = arrierePlans == null ? new ArrayList<>() : arrierePlans;
        this.lesEntites = lesEntites == null ? new ArrayList<>() : lesEntites;
        this.pointDepart = pointDepart;
        this.pointArrivee = pointsArrivee;
        this.tempsImparti = tempsImparti <= 0 ? TEMPS_IMPARTI_PAR_DEFAUT : tempsImparti;
        numeroNiveau = nombreNiveaux;
        nombreNiveaux++;
    }

    /**
     * Retourne le numero d'un niveau
     * @return
     */
    public int getNumeroNiveau() {
        return numeroNiveau;
    }

    /**
     * retourne la carte 2D du niveau
     * @return
     */
    public Carte2D getCarte() {
        return carte;
    }

    /**
     * Retourne la liste des arrières plans du niveau
     * @return
     */
    public List<ArrierePlan> getLesArrieresPlans() {
        return Collections.unmodifiableList(lesArrieresPlans);
    }

    /**
     * Retourne la liste des entitées du niveau
     * @return
     */
    public List<Entite> getLesEntites() {
        return Collections.unmodifiableList(lesEntites);
    }

    public List<Score> getLesScores() {
        return Collections.unmodifiableList(lesScores);
    }

    /**
     * retourne la position du point de départ
     * @return
     */
    public Position2D getPointDepart() {
        return pointDepart;
    }

    public Position2D getPointArrivee() {
        return pointArrivee;
    }

    public int getTempsImparti() {
        return tempsImparti;
    }

    /**
     * Ajoute un nouveau score au niveau
     * @param score score a ajouter au niveau
     */
    public void ajouterScore(Score score) {
        lesScores.add(score);
        if (lesScores.size() > NOMBRE_SCORE_MAXIMUM) {
            lesScores.remove(NOMBRE_SCORE_MAXIMUM);
        }
    }

    public void ajouterEntites(List<Entite> lesEntites) {
        if (lesEntites != null) {
            for (Entite entite : lesEntites) {
                ajouterEntite(entite);
            }
        }
    }

    public void ajouterEntite(Entite entite) {
        if (entite != null) {
            lesEntites.add(entite);
            notifier(entite, TypeNotification.AJOUT);
        }
    }

    public void retirerEntite(Entite entite) {
        if (entite != null) {
            boolean presence = lesEntites.remove(entite);
            if (presence) {
                notifier(entite, TypeNotification.SUPPRESSION);
            }
        }
    }

    /**
     * Compare au niveau objet deux niveau
     * @param o objet a comparé ici Niveau
     * @return un boolean selon la comparaison. True si identique , false si différent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Niveau niveau = (Niveau) o;
        return equals(niveau);
    }

    /**
     * Compare un niveau avec le niveau actuel
     * @param niveau
     * @return
     */
    public boolean equals(Niveau niveau) {
        return numeroNiveau == niveau.getNumeroNiveau();
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + numeroNiveau ^ (numeroNiveau >>> 16);
        return resultat;
    }


    /**
     * Affichage des données du Niveau
     * @return
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("Niveau [");
        chaine.append(numeroNiveau);
        chaine.append("] : ");
        chaine.append("\nCarte : ");
        chaine.append(carte.toString());
        chaine.append("\nArrieresPlans : ");
        for (ArrierePlan arrierePlan : lesArrieresPlans) {
            chaine.append(arrierePlan.toString());
            chaine.append(", ");
        }
        chaine.append("\nEntites : ");
        for (Entite entite : lesEntites) {
            chaine.append(entite.toString());
            chaine.append(", ");
        }
        chaine.append("\nMeilleurs scores : ");
        for (Score score : lesScores) {
            chaine.append(score.toString());
            chaine.append(", ");
        }
        chaine.append("\nPosition de départ : ");
        chaine.append(pointDepart.toString());
        chaine.append("\nPosition d'arrivée : ");
        chaine.append(pointArrivee.toString());
        return chaine.toString();
    }
}
