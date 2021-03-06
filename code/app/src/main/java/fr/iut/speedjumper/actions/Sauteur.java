package fr.iut.speedjumper.actions;

import fr.iut.speedjumper.actions.collisionneurs.CollisionneurCarte;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.BoucleDeJeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;

public class Sauteur implements Simulation, Runnable {
    private static final float DELTA = 1f / BoucleDeJeu.FPS_CIBLE;
    private static final float HAUTEUR_MAXIMALE_SAUT = 900;
    private static final float HAUTEUR_SAUT = 700;
    private static final float DUREE_SAUT = 0.64f;
    private CollisionneurCarte collisionneur;
    private TableauJeu tableauJeu;
    private Entite entite;
    private double temps;

    public Sauteur(TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Impossible d'instancier un sauteur car le tableau de "
                    + "jeu passé en paramètre est null.");
        }
        this.tableauJeu = tableauJeu;
        collisionneur = new CollisionneurCarte();
    }

    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        this.entite = entite;
        this.temps = temps;
    }

    /**
     * lancemement de l'action
     */
    @Override
    public void run() {
        if (entite.isChute() || !entite.isSurSol()) {
            return;
        }

        entite.setChute(true);
        entite.setSurSol(false);

        float gravite = (2 * HAUTEUR_MAXIMALE_SAUT) / (DUREE_SAUT * DUREE_SAUT);
        float velociteSaut = (float)Math.sqrt(2 * gravite * HAUTEUR_SAUT);

        float velocite = -velociteSaut;
        float position = 0, positionPrecedente = 0;

        Rectangle collisionFuture;

        while (velocite < 0 || entite.isChute()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            velocite += gravite * DELTA;
            position += velocite * DELTA;

            collisionFuture = new Rectangle(entite.getCollision().getPosition().getX(),
                    entite.getCollision().getPosition().getY() + (position - positionPrecedente),
                    entite.getCollision().getDimension());

            if (collisionneur.collisionne(collisionFuture, tableauJeu.getNiveauCourant().getCarte())) {
                entite.setChute(true);
                return;
            }
            else {
                entite.setPosition(new Position2D(entite.getPosition().getX(), entite.getPosition().getY()
                        + (position - positionPrecedente)));
            }
            positionPrecedente = position;
        }
        entite.setChute(true);
    }
}
