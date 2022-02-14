package fr.iut.speedjumper.entites;

import androidx.annotation.NonNull;

import fr.iut.speedjumper.actions.collisionneurs.VisiteurCollisions;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Direction;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.observateurs.SujetEntite;
import fr.iut.speedjumper.observateurs.TypeNotification;

public abstract class Entite extends SujetEntite {
    private static final double VELOCITE_PAR_DEFAUT = 1;
    private Position2D position;
    private Rectangle collision;
    private Dimension dimension;
    private Direction direction;
    private double velocite;
    private boolean surSol;
    private boolean chute;

    public Entite(Position2D position, Rectangle collision, Dimension dimension,
                  double velocite) throws IllegalArgumentException {
        verificationParametre(position, "position");
        verificationParametre(dimension, "dimension");
        verificationParametre(collision, "collision");

        if (dimension.getLargeur() <= 0 || dimension.getHauteur() <= 0
                || dimension.getLargeur() < collision.getDimension().getLargeur()
                || dimension.getHauteur() < collision.getDimension().getHauteur()) {
            throw new IllegalArgumentException("La dimension passée en paramètre de l'entité est nulle ou "
                    + "inférieure à 0, ou inférieure à la collision de l'entité. Donné : " + dimension);
        }

        this.dimension = dimension;
        this.position = position;
        this.collision = collision;
        this.velocite = velocite <= 0 ? VELOCITE_PAR_DEFAUT : velocite;
        direction = Direction.DROITE;
        surSol = false;
        chute = true;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
        collision.getPosition().ajouterPosition(position);
        notifier(this, TypeNotification.MODIFICATION);
    }

    public Rectangle getCollision() {
        return collision;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public double getVelocite() {
        return velocite;
    }

    public boolean isSurSol() {
        return surSol;
    }

    public boolean isChute() {
        return chute;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSurSol(boolean surSol) {
        this.surSol = surSol;
    }

    public void setChute(boolean chute) {
        this.chute = chute;
    }

    public abstract void miseAJour(double temps);

    public void accepte(VisiteurCollisions visiteur, Entite entite) {
        entite.accepte(visiteur, this);
    }

    public abstract void accepte(VisiteurCollisions visiteur, ObjetInteractif objet);

    public abstract void accepte(VisiteurCollisions visiteur, PersonnageJouable joueur);

    public abstract void accepte(VisiteurCollisions visiteur, Ennemi ennemi);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return equals(entite);
    }

    public boolean equals(Entite entite) {
        return position.equals(entite.getPosition())
                && dimension.equals(entite.getDimension())
                && collision.equals(entite.getCollision())
                && direction.equals(entite.getDirection());
    }

    @Override
    public int hashCode() {
        final int premier = 31;
        int resultat = 1;
        resultat = premier * resultat + ((position == null) ? 0 : position.hashCode());
        resultat = premier * resultat + ((dimension == null) ? 0 : dimension.hashCode());
        resultat = premier * resultat + ((collision == null) ? 0 : collision.hashCode());
        resultat = premier * resultat + ((direction == null) ? 0 : direction.hashCode());
        return resultat;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getClass() + " : " + position.toString() + " "
                + "\nZone collision : " + collision.toString()
                + "\n" + velocite + "v"
                + "\n" + "Se trouve sur sol : " + surSol
                + "\n" + "Est en train de chuter : " + chute
                + "\nDirection : " + direction;
    }

    private void verificationParametre(Object o, String nom) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException("La " + nom + " passée en paramètre est nulle.");
        }
    }
}
