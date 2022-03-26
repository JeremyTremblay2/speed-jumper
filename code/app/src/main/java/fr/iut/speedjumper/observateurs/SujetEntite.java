package fr.iut.speedjumper.observateurs;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.entites.Entite;

public abstract class SujetEntite {
    private List<ObservateurEntite> lesObservateurs;

    /**
     * Constructeur du sujet.
     * Créer une ArrayList d'observateur
     */
    public SujetEntite() {
        lesObservateurs = new ArrayList<>();
    }

    /**
     * Attache un observateur au sujet
     * @param o Observateur auquel attaché le sujet
     */
    public void attacher(ObservateurEntite o) {
        if (!lesObservateurs.contains(o)) {
            lesObservateurs.add(o);
        }
    }


    /**
     * Dettache l'observateur du sujet
     * @param o Observateur auquel dettaché le sujet
     */
    public void detacher(ObservateurEntite o) {
        lesObservateurs.remove(o);
    }

    /**
     * Methode pour notifier et mettre a jour les observateurs
     */
    public void notifier(Entite entite, TypeNotification type) {
        for (ObservateurEntite o : lesObservateurs) {
            o.miseAJour(entite, type);
        }
    }

}
