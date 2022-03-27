package fr.iut.speedjumper.jeu;

import static java.lang.Thread.sleep;

import fr.iut.speedjumper.observateurs.Sujet;
import fr.iut.speedjumper.observateurs.SujetTemporel;

/**
 * Classe de la boucle du jeu
 */
public class BoucleDeJeu extends SujetTemporel implements Runnable {
    public static final int FPS_CIBLE = 60;
    public final long tempsLancement;
    private static final long TEMPS_NANOSECONDE = 1000000000;
    private static final long TEMPS_MILLISECONDE = 1000000;
    public static final long TEMPS_AVANT_NOTIFICATION = TEMPS_NANOSECONDE / FPS_CIBLE;

    private boolean actif;
    private long tempsCourant;
    private long dernierTemps;
    private long tempsEcoule;
    private long tempsEcouleTotal;

    public BoucleDeJeu() {
        tempsLancement = System.nanoTime();
        tempsEcouleTotal = 0;
        actif = false;
    }

    public long getTempsEcouleTotal() {
        return tempsEcouleTotal;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Override
    public void run() {
        actif = true;
        dernierTemps = System.nanoTime();
        long tempsAttente;

        while(actif) {
            tempsCourant = System.nanoTime();
            tempsEcoule = tempsCourant - dernierTemps;
            if (tempsEcoule >= TEMPS_AVANT_NOTIFICATION) {
                ticker(tempsEcoule);
                tempsEcouleTotal += tempsEcoule;
                dernierTemps = tempsCourant;
            }
            else {
                tempsAttente = TEMPS_AVANT_NOTIFICATION - tempsEcoule;
                try {
                    sleep(tempsAttente / TEMPS_MILLISECONDE,
                            (int) (tempsAttente % TEMPS_MILLISECONDE));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ticker(double timer) {
        notifier(timer);
    }
}
