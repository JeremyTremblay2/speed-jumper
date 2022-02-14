package fr.iut.speedjumper.jeu;

import fr.iut.speedjumper.entrees.RecuperateurDeTouches;
import fr.iut.speedjumper.observateurs.Observateur;

/**
 * Classe gerant le jeu
 */
public class Jeu implements Observateur {
    private static final int FPS_CIBLE = 60;
    private static final double TEMPS_MISE_A_JOUR = 10000000;
    private boolean joue;
    private TableauJeu jeu;
    private BoucleDeJeu boucleDeJeu;
    private Thread processus;

    /**
     * Constructeur de Jeu
     * @param recuperateur recuperateur de touche
     */
    public Jeu(RecuperateurDeTouches recuperateur) {
        jeu = new TableauJeu(recuperateur);
        joue = true;
    }

    /**
     * retourne le jeu
     * @return
     */
    public TableauJeu getJeu() {
        return jeu;
    }

    public void initialise() {
        //Si des chargements particuliers ont lieux ici.
    }

    /**
     * Methode pour lancer le jeu
     */
    public void jouer() {
        boucleDeJeu = new BoucleDeJeu();
        boucleDeJeu.attacher(this);
        processus = new Thread(boucleDeJeu, "Speed Jumper Thread");
        processus.start();
    }

    /**
     * Methode appelé a la fermeture du jeu
     */
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
    }
}