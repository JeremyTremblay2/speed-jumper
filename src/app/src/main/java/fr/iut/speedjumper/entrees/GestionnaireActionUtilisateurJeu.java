package fr.iut.speedjumper.entrees;

/**
 * Classe gerant les actions utilisateur pendant le jeu
 */
import java.util.List;

import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.monde.Niveau;

public class GestionnaireActionUtilisateurJeu extends GestionnaireActionUtilisateur {
    private Commande flecheGauche;
    private Commande flecheDroite;
    private Commande espace;
    private Commande aucuneAction;

    public GestionnaireActionUtilisateurJeu(RecuperateurDeTouches recuperateur, TableauJeu tableauJeu)
            throws IllegalArgumentException {
        super(recuperateur, tableauJeu);
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, tableauJeu);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, tableauJeu);
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
        if (lesTouches.contains(Touche.ECHAP)) {
            pause = true;
        }
        return lesCommandes;
    }
}
