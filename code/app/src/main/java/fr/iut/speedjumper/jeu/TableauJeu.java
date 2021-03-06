package fr.iut.speedjumper.jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.iut.speedjumper.comportement.ComportementNull;
import fr.iut.speedjumper.donnees.AdaptateurChargeurDeCarteTiledCSV;
import fr.iut.speedjumper.donnees.ChargeurDeJeuxDeTuilesTextuel;
import fr.iut.speedjumper.donnees.ChargeurEnnemis;
import fr.iut.speedjumper.donnees.ChargeurEnnemisStub;
import fr.iut.speedjumper.donnees.ChargeurScoreTextuel;
import fr.iut.speedjumper.donnees.CollectionRessources;
import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.entites.PersonnageJouable;
import fr.iut.speedjumper.entrees.RecuperateurDeTouches;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.logique.Score;
import fr.iut.speedjumper.monde.Carte2D;
import fr.iut.speedjumper.monde.Niveau;

/**
 * Classe tableau de jeu permettant de les jeux
 */
public class TableauJeu {
    private GestionnaireDeRessources gestionnaireDeRessources;
    private List<Niveau> lesNiveaux = new ArrayList<>();
    private PersonnageJouable joueur;
    private Niveau niveauCourant;
    private Options options;

    public TableauJeu(GestionnaireDeRessources gestionnaireDeRessources) throws IllegalArgumentException {
        if (gestionnaireDeRessources == null) {
            throw new IllegalArgumentException("Le gestionnaire de ressource ne peut pas être null.");
        }
        this.gestionnaireDeRessources = gestionnaireDeRessources;
        initialisation();
    }

    /**
     * Methode pour savoir si la partie est perdue
     * @return
     */
    public boolean isGameOver() {
        return joueur.getPointsDeVie() <= 0;
    }

    /**
     * Retourne le niveau courant
     * @return
     */
    public Niveau getNiveauCourant() {
        return niveauCourant;
    }

    /**
     * set le niveau courrant
     * @param niveau numero du niveau
     */
    public void setNiveauCourant(int niveau) {
        niveauCourant = lesNiveaux.get(niveau);
        joueur.setPointsDeVie(joueur.getPointsDeViesInitiaux());
        joueur.setPosition(niveauCourant.getPointDepart());
    }

    /**
     * retourne le joueur
     * @return
     */
    public PersonnageJouable getJoueur() {
        return joueur;
    }

    /**
     * retourle le gestionnaire de ressource
     * @return
     */
    public GestionnaireDeRessources getGestionnaireDeRessources() {
        return gestionnaireDeRessources;
    }

    /**
     * retourne la liste des niveaux
     * @return
     */
    public List<Niveau> getLesNiveaux() {
        return Collections.unmodifiableList(lesNiveaux);
    }

    /**
     * retourne les options
     * @return
     */
    public Options getOptions() {
        return options;
    }

    public void changerNiveau(int niveau) {
        try {
            niveauCourant = lesNiveaux.get(niveau);
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * initialise les différents niveaux avec leur carte
     */
    private void initialisation() {
        List<Carte2D> lesCartes = new ArrayList<>();
        List<List<Score>> lesScores = new ArrayList<>();
        options = new Options(true, 10, 10);
        Niveau niveau;

        try {
            gestionnaireDeRessources.charge();
            lesCartes = gestionnaireDeRessources.getLesCartes();
            lesScores = gestionnaireDeRessources.getLesScores();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CollectionRessources ressources = CollectionRessources.getInstance();
        List<Position2D> lesPointsDepart = ressources.getLesPointsDepart();
        List<Position2D> lesPointsArrivee = ressources.getLesPointsArrivee();

        for (int i = 0; i < lesCartes.size(); i++) {
            niveau = new Niveau(lesCartes.get(i),
                    null,
                    null,
                    lesScores.get(i) == null ? null : lesScores.get(i),
                    lesPointsDepart.get(i),
                    lesPointsArrivee.get(i),
                    300);
            lesNiveaux.add(niveau);
        }

        ChargeurEnnemis chargeurEnnemis = new ChargeurEnnemisStub(this);
        List<List<Entite>> lesEnnemis = chargeurEnnemis.charge(null);
        for (int i = 0; i < lesNiveaux.size(); i++) {
            lesNiveaux.get(i).ajouterEntites(lesEnnemis.get(i));
        }

        joueur = new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(22, 12, 41, 112),
                new Dimension(85, 128),
                6,
                4,
                3);

        setNiveauCourant(0);
    }
}
