package fr.iut.speedjumper.entrees;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.monde.Niveau;

/**
 * Classe abstraite gérant les actions utilisateur s
 */
public abstract class GestionnaireActionUtilisateur {
    protected RecuperateurDeTouches recuperateurDeTouches;
    protected List<Touche> lesTouches;
    protected List<Commande> lesCommandes;
    protected TableauJeu tableauJeu;
    protected boolean pause;

    public GestionnaireActionUtilisateur(RecuperateurDeTouches recuperateur, TableauJeu tableauJeu)
            throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas être null.");
        }
        this.tableauJeu = tableauJeu;
        recuperateurDeTouches = recuperateur;
        lesCommandes = new ArrayList<>();
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public RecuperateurDeTouches getRecuperateurDeTouches() {
        return recuperateurDeTouches;
    }

    public abstract List<Commande> attribuerAction();
}

