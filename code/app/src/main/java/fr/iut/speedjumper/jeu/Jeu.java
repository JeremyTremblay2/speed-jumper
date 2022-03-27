package fr.iut.speedjumper.jeu;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;

import fr.iut.speedjumper.actions.Chuteur;
import fr.iut.speedjumper.actions.collisionneurs.CollisionneurPointRectangle;
import fr.iut.speedjumper.actions.collisionneurs.GestionnaireDeCollisions;
import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisions;
import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisionsBasique;
import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.entrees.Commande;
import fr.iut.speedjumper.entrees.GestionnaireActionUtilisateur;
import fr.iut.speedjumper.entrees.GestionnaireActionUtilisateurDebug;
import fr.iut.speedjumper.entrees.GestionnaireActionUtilisateurJeu;
import fr.iut.speedjumper.entrees.RecuperateurDeTouches;
import fr.iut.speedjumper.observateurs.Observateur;
import fr.iut.speedjumper.observateurs.ObservateurTemporel;
import fr.iut.speedjumper.observateurs.Sujet;

/**
 * Classe gerant le jeu
 */
public class Jeu extends Sujet implements ObservateurTemporel {
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
    public Jeu(RecuperateurDeTouches recuperateur, GestionnaireDeRessources gestionnaireDeRessources)
            throws IllegalArgumentException {
        tableauJeu = new TableauJeu(gestionnaireDeRessources);
        gestionnaireActions = new GestionnaireActionUtilisateurJeu(recuperateur, tableauJeu);
        VisiteurCollisions visiteur = new VisiteurCollisionsBasique(tableauJeu);
        gestionnaireDeCollisions = new GestionnaireDeCollisions(tableauJeu, visiteur);
        collisionneurPointRectangle = new CollisionneurPointRectangle();
        chuteur = new Chuteur(tableauJeu);
        boucleDeJeu = new BoucleDeJeu();
        boucleDeJeu.attacher(this);
        pause = true;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isLance() {
        return processus != null && processus.isAlive();
    }

    public TableauJeu getTableauJeu() {
        return tableauJeu;
    }

    public void lancerJeu() throws IllegalStateException {
        if (processus != null && processus.isAlive()) {
            throw new IllegalStateException("Le thread du jeu est déjà lancé et doit d'abord être interrompu.");
        }
        boucleDeJeu.setActif(true);
        pause = false;
        processus = new Thread(boucleDeJeu, "Speed Jumper Thread");
        processus.start();
        Log.d("SpeedJumper", tableauJeu.getJoueur().getPosition().toString());
    }

    public void arreterJeu() {
        boucleDeJeu.setActif(false);
        try {
            if (isLance()) {
                processus.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pause = true;
    }

    public void changerNiveau(int niveau) {
        if (tableauJeu.getLesNiveaux().size() >= niveau) {
            tableauJeu.changerNiveau(niveau);
            gestionnaireDeCollisions.initialisation();
        }
    }

    @Override
    public void miseAJour(double temps) {
        if (pause) {
            return;
        }
        entreeUtilisateur(temps);
        gestionEntites(temps);
        notifier();

        /*double positionPersonnageY = joueur.getPosition().getY() / niveauCourant.getCarte().getDimensionTuiles().getHauteur();
        if (positionPersonnageY >= niveauCourant.getCarte().getDimensionCarte().getHauteur() - 1) {
            joueur.setPointsDeVie(0);
        }*/
    }

    private void entreeUtilisateur(double temps) {
        if (tableauJeu.isGameOver()) {
            gestionnaireActions.setPause(true);
        }

        List<Commande> actions = gestionnaireActions.attribuerAction();
        for (Commande commande : actions) {
            commande.execute(tableauJeu.getJoueur(), temps);
        }
    }

    private void gestionEntites(double temps) {
        List<Entite> lesEntites = tableauJeu.getNiveauCourant().getLesEntites();
        //lesEntites.add(tableauJeu.getJoueur());
        for (Entite entite : lesEntites) {
            chuteur.miseAJourEtatDeJeu(entite, temps);
            entite.miseAJour(temps);
        }
        chuteur.miseAJourEtatDeJeu(tableauJeu.getJoueur(), temps);
        new Thread(chuteur).start();
    }
}