package fr.iut.speedjumper.actions.collisionneurs;

import fr.iut.speedjumper.entites.Ennemi;
import fr.iut.speedjumper.entites.ObjetInteractif;
import fr.iut.speedjumper.entites.PersonnageJouable;
import fr.iut.speedjumper.jeu.TableauJeu;

public class VisiteurCollisionsBasique extends VisiteurCollisions {
    private TableauJeu tableauJeu;

    public VisiteurCollisionsBasique(TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu fournit en paramètre ne peut pas "
                    + "être null.");
        }
        this.tableauJeu = tableauJeu;
    }

    @Override
    public void visite(Ennemi ennemi1, Ennemi ennemi2) {
        //Ne fait rien.
    }

    @Override
    public void visite(Ennemi ennemi, ObjetInteractif objet) {
        //Ne fait rien.
    }

    @Override
    public void visite(Ennemi ennemi, PersonnageJouable joueur) {
        int vie = joueur.getPointsDeVie() - ennemi.getDegats();
        if (vie < 0) {
            vie = 0;
        }
        joueur.setPointsDeVie(vie);
    }

    @Override
    public void visite(PersonnageJouable joueur, Ennemi ennemi) {
        visite(ennemi, joueur);
    }

    @Override
    public void visite(PersonnageJouable joueur, ObjetInteractif objet) {
        objet.appliquerEffets(joueur);
        tableauJeu.getNiveauCourant().retirerEntite(objet);
    }

    @Override
    public void visite(PersonnageJouable joueur1, PersonnageJouable joueur2) {
        //Ne fait rien.
    }

    @Override
    public void visite(ObjetInteractif objet, Ennemi ennemi) {
        visite(ennemi, objet);
    }

    @Override
    public void visite(ObjetInteractif objet1, ObjetInteractif objet2) {
        //Ne fait rien
    }

    @Override
    public void visite(ObjetInteractif objet, PersonnageJouable joueur) {
        visite(joueur, objet);
    }
}
