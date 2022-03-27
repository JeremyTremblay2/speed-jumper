package fr.iut.speedjumper.jeu;

import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entrees.RecuperateurDeTouches;
import fr.iut.speedjumper.observateurs.Observateur;

public class GestionnaireDeJeu {
    private Jeu jeu;
    private boolean pause;

    public GestionnaireDeJeu(RecuperateurDeTouches recuperateurDeTouches,
                             GestionnaireDeRessources gestionnaireDeRessources) throws IllegalArgumentException {
        jeu = new Jeu(recuperateurDeTouches, gestionnaireDeRessources);
    }

    public TableauJeu getTableauJeu() {
        return jeu.getTableauJeu();
    }

    public boolean isLance() {
        return jeu.isLance();
    }

    public void lancerJeu() throws IllegalArgumentException {
        jeu.lancerJeu();
    }

    public void fermerJeu() {
        jeu.arreterJeu();
    }

    public void changerNiveau(int niveau) {
        jeu.changerNiveau(niveau);
    }

    public void attacher(Observateur observateur) {
        jeu.attacher(observateur);
    }

    public void detacher(Observateur observateur) {
        jeu.detacher(observateur);
    }


    public void setPause(boolean pause) {
        jeu.setPause(pause);
    }
}
