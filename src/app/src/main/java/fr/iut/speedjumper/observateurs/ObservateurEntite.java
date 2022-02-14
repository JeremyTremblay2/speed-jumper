package fr.iut.speedjumper.observateurs;

import fr.iut.speedjumper.entites.Entite;

public interface ObservateurEntite {
    void miseAJour(Entite entite, TypeNotification type);
}
