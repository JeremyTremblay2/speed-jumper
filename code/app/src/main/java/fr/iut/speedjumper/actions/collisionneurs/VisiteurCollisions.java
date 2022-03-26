package fr.iut.speedjumper.actions.collisionneurs;

import fr.iut.speedjumper.entites.Ennemi;
import fr.iut.speedjumper.entites.ObjetInteractif;
import fr.iut.speedjumper.entites.PersonnageJouable;

public abstract class VisiteurCollisions {
    public abstract void visite(Ennemi ennemi1, Ennemi ennemi2);
    public abstract void visite(Ennemi ennemi, ObjetInteractif objet);
    public abstract void visite(Ennemi ennemi, PersonnageJouable joueur);
    public abstract void visite(PersonnageJouable joueur, Ennemi ennemi);
    public abstract void visite(PersonnageJouable joueur, ObjetInteractif objet);
    public abstract void visite(PersonnageJouable joueur1, PersonnageJouable joueur2);
    public abstract void visite(ObjetInteractif objet, Ennemi ennemi);
    public abstract void visite(ObjetInteractif objet1, ObjetInteractif objet2);
    public abstract void visite(ObjetInteractif objet, PersonnageJouable joueur);
}
