package fr.iut.speedjumper.observateurs;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.entites.Entite;

public abstract class SujetTemporel {
    private List<ObservateurTemporel> lesObservateurs;

    public SujetTemporel() {
        lesObservateurs = new ArrayList<>();
    }

    public void attacher(ObservateurTemporel o) {
        if (!lesObservateurs.contains(o)) {
            lesObservateurs.add(o);
        }
    }


    /**
     * Dettache l'observateur du sujet
     * @param o Observateur auquel dettaché le sujet
     */
    public void detacher(ObservateurTemporel o) {
        lesObservateurs.remove(o);
    }

    /**
     * Methode pour notifier et mettre a jour les observateurs
     */
    public void notifier(double temps) {
        for (ObservateurTemporel o : lesObservateurs) {
            o.miseAJour(temps);
        }
    }
}
