package fr.iut.speedjumper.jeu;

import fr.iut.speedjumper.entrees.RecuperateurDeTouches;

public class GestionnaireDeJeu {
    private Jeu jeu;
    private boolean pause;

    public GestionnaireDeJeu(RecuperateurDeTouches recuperateurDeTouches) {
        jeu = new Jeu(recuperateurDeTouches);
    }

    public void initialise() {
        jeu.initialise();
    }

    public void lance() {
        jeu.lance();
    }

    public void ferme() {
        jeu.ferme();
    }

    public void setPause(boolean pause) {
        jeu.setPause(pause);
    }
}
