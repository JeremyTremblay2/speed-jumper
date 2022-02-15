package fr.iut.speedjumper.jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.iut.speedjumper.actions.Chuteur;
import fr.iut.speedjumper.actions.collisionneurs.CollisionneurPointRectangle;
import fr.iut.speedjumper.actions.collisionneurs.GestionnaireDeCollisions;
import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisions;
import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisionsBasique;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.entites.Vivant;
import fr.iut.speedjumper.entrees.Commande;
import fr.iut.speedjumper.entrees.GestionnaireActionUtilisateur;
import fr.iut.speedjumper.entrees.GestionnaireActionUtilisateurDebug;
import fr.iut.speedjumper.entrees.RecuperateurDeTouches;
import fr.iut.speedjumper.observateurs.Observateur;
import fr.iut.speedjumper.observateurs.Sujet;

/**
 * Classe gerant le jeu
 */
public class Jeu extends Sujet implements Observateur {
    private boolean pause;
    private Chuteur chuteur;
    private GestionnaireActionUtilisateur gestionnaireActions;
    private GestionnaireDeCollisions gestionnaireDeCollisions;
    private CollisionneurPointRectangle collisionneurPointRectangle;
    private TableauJeu tableauJeu;
    private BoucleDeJeu boucleDeJeu;
    private Thread processus;

    /**
     * Constructeur de Jeu
     * @param recuperateur recuperateur de touche
     */
    public Jeu(RecuperateurDeTouches recuperateur) {
        tableauJeu = new TableauJeu();
        gestionnaireActions = new GestionnaireActionUtilisateurDebug(recuperateur, tableauJeu);
        VisiteurCollisions visiteur = new VisiteurCollisionsBasique(tableauJeu);
        gestionnaireDeCollisions = new GestionnaireDeCollisions(tableauJeu, visiteur);
        collisionneurPointRectangle = new CollisionneurPointRectangle();
        chuteur = new Chuteur(tableauJeu);
        pause = false;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void initialise() {
        //Si des chargements particuliers ont lieux ici.
    }

    public void lance() {
        boucleDeJeu = new BoucleDeJeu();
        boucleDeJeu.attacher(this);
        processus = new Thread(boucleDeJeu, "Speed Jumper Thread");
        processus.start();
    }

    public void changerNiveau(int niveau) {
        if (tableauJeu.getLesNiveaux().size() >= niveau) {
            tableauJeu.changerNiveau(niveau);
            gestionnaireDeCollisions.initialisation();
        }
    }

    public void ferme() {
        //Eventuellement sauvegarde, etc avant de quitter.
        boucleDeJeu.setEnCours(false);
        try {
            processus.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void miseAJour() {
        double temps = boucleDeJeu.getTempsEcoule();
        entreeUtilisateur(temps);

        gestionEntites(temps);

        /*double positionPersonnageY = joueur.getPosition().getY() / niveauCourant.getCarte().getDimensionTuiles().getHauteur();
        if (positionPersonnageY >= niveauCourant.getCarte().getDimensionCarte().getHauteur() - 1) {
            joueur.setPointsDeVie(0);
        }*/
    }

    private void entreeUtilisateur(double temps) {
        List<Commande> actions = gestionnaireActions.attribuerAction();
        for (Commande commande : actions) {
            commande.execute(tableauJeu.getJoueur(), temps);
        }

        if (tableauJeu.isGameOver()) {
            gestionnaireActions.setPause(true);
            notifier();
        }
    }

    private void gestionEntites(double temps) {
        List<Entite> lesEntites = tableauJeu.getNiveauCourant().getLesEntites();
        lesEntites.add(tableauJeu.getJoueur());
        for (Entite entite : lesEntites) {
            chuteur.miseAJourEtatDeJeu(entite, temps);
            entite.miseAJour(temps);
        }
    }
}