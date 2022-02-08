package fr.iut.speedjumper.entrees;

import fr.iut.speedjumper.entites.Entite;

/**
 * Classe permettant d'executer une commande sur une entite
 */
public interface Commande {
    void execute(Entite entite, float temps);
}
