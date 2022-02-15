package fr.iut.speedjumper.actions.collisionneurs;

import java.util.Set;

import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.observateurs.ObservateurEntite;
import fr.iut.speedjumper.observateurs.TypeNotification;

public class GestionnaireDeCollisions implements ObservateurEntite {
    private Set<Entite> lesEntites;
    private TableauJeu tableauJeu;
    private VisiteurCollisions visiteur;

    public GestionnaireDeCollisions(TableauJeu tableauJeu, VisiteurCollisions visiteur)
            throws IllegalArgumentException {
        if (tableauJeu == null || visiteur == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ou le visiteur "
                    + "de collisions ne peuvent pas être null.");
        }
        this.tableauJeu = tableauJeu;
        this.visiteur = visiteur;
        initialisation();
    }

    public void initialisation() {
        lesEntites.clear();
        tableauJeu.getNiveauCourant().attacher(this);
        lesEntites.addAll(tableauJeu.getNiveauCourant().getLesEntites());
        lesEntites.add(tableauJeu.getJoueur());
    }

    @Override
    public void miseAJour(Entite entite, TypeNotification type) {
        switch (type) {
            case AJOUT:
                lesEntites.add(entite);
            case SUPPRESSION:
                lesEntites.remove(entite);
            case MODIFICATION:
                for (Entite e : lesEntites) {
                    entite.accepte(visiteur, e);
                }
        }
    }
}
