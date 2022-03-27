package fr.iut.speedjumper.jeu;

import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entrees.RecuperateurDeTouches;

public class GestionnaireDeJeu {
    private Jeu jeu;
    private boolean pause;

    public GestionnaireDeJeu(RecuperateurDeTouches recuperateurDeTouches,
                             GestionnaireDeRessources gestionnaireDeRessources) throws IllegalArgumentException {
        jeu = new Jeu(recuperateurDeTouches, gestionnaireDeRessources);
    }

    public TableauJeu getTableaJeu() {
        return jeu.getTableauJeu();
    }

    public void lance() throws IllegalArgumentException {
        jeu.lancerJeu();
    }

    public void ferme() {
        jeu.arreterJeu();
    }

    public void setPause(boolean pause) {
        jeu.setPause(pause);
    }
}
