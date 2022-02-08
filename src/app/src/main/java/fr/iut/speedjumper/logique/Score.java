package fr.iut.speedjumper.logique;

import fr.iut.speedjumper.entites.Piece;

import java.util.Objects;

/**
 * Classe du score d'un joueur
 */
public class Score implements Comparable<Score> {
    private static final String PSEUDO_PAR_DEFAUT = "Joueur";
    private int score;
    private String pseudo;

    /**
     * Constructeur du Score
     * @param pseudo pseudo de la personne ayant le score
     * @param score score du joueur
     */
    public Score(String pseudo, int score) {
        this.score = score;
        this.pseudo = pseudo;
    }

    /**
     * Autre constructeur du Score si le pseudo n'est pas donné
     * @param score Score du joueur
     */
    public Score(int score) {
        this(PSEUDO_PAR_DEFAUT, score);
    }

    /**
     * Autre constructeur du Score si no le pseudo ni le score n'est renseigné
     */
    public Score() {
        this(PSEUDO_PAR_DEFAUT, 0);
    }

    public int getScore() {
        return score;
    }

    public String getPseudo() {
        return pseudo;
    }

    /**
     * Methode pour augmenter le score d'un joueur par rapport aux pièces
     * @param piece valeur du score a rajouter
     */
    public void augmenterScore(Piece piece) {
        score += piece.getValeur();
    }

    /**
     * Methode pour augmenter le score d'un joueur par rapport au temps
     * @param temps temps final de réalisation du niveau
     */
    public void augmenterScore(double temps) {
        score += (temps / 1000000000);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return equals(score);
    }

    public boolean equals(Score score) {
        return this.score == score.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return pseudo + " : " + score + " points.";
    }

    @Override
    public int compareTo(Score score) {
        int comp = 0;
        if (this.score > score.getScore())
            comp = +1;
        else if (this.score < score.getScore())
            comp = -1;
        return comp;
    }
}
