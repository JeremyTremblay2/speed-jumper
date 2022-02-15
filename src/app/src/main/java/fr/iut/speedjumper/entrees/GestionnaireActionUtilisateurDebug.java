package fr.iut.speedjumper.entrees;

import java.util.List;

import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.monde.Niveau;

public class GestionnaireActionUtilisateurDebug extends GestionnaireActionUtilisateur {
    private Commande flecheGauche;
    private Commande flecheDroite;
    private Commande flecheBas;
    private Commande flecheHaut;
    private Commande espace;
    private Commande aucuneAction;

    public GestionnaireActionUtilisateurDebug(RecuperateurDeTouches recuperateur, TableauJeu tableauJeu)
            throws IllegalArgumentException {
        super(recuperateur, tableauJeu);
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, tableauJeu);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, tableauJeu);
        flecheBas = new CommandeDeplacement(Direction.BAS, tableauJeu);
        flecheHaut = new CommandeDeplacement(Direction.HAUT, tableauJeu);
        espace = new CommandeSaut(tableauJeu);
        aucuneAction = new CommandeNulle();
    }

    @Override
    public List<Commande> attribuerAction() {
        lesTouches = recuperateurDeTouches.detecte();
        lesCommandes.clear();

        if (lesTouches.contains(Touche.ESPACE)) {
            lesCommandes.add(espace);
        }
        if (lesTouches.contains(Touche.FLECHE_DROITE)) {
            lesCommandes.add(flecheDroite);
        }
        if (lesTouches.contains(Touche.FLECHE_GAUCHE)) {
            lesCommandes.add(flecheGauche);
        }
        if (lesTouches.contains(Touche.FLECHE_HAUT)) {
            lesCommandes.add(flecheHaut);
        }
        if (lesTouches.contains(Touche.FLECHE_BAS)) {
            lesCommandes.add(flecheBas);
        }
        if (lesTouches.contains(Touche.ECHAP)) {
            pause = true;
        }
        return lesCommandes;
    }
}
