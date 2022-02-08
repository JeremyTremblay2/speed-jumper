package fr.iut.speedjumper.donnees;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.comportement.ComportementMarche;
import fr.iut.speedjumper.entites.Ennemi;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.monde.Niveau;

/**
 * Classe pour charger des enemis
 */
public class ChargeurEnnemisStub implements ChargeurEnnemis {
    private List<List<Entite>> lesEnnemis;
    private List<Niveau> lesNiveau;

    /**
     * constructeur de la classe
     * @param lesNiveau niveau ou seront les enemis
     * @throws IllegalArgumentException
     */
    public ChargeurEnnemisStub(List<Niveau> lesNiveau) throws IllegalArgumentException {
        if (lesNiveau == null) {
            throw new IllegalArgumentException("Les niveaux passés en paramètre ne peuvent pas être null.");
        }
        this.lesNiveau = lesNiveau;
        lesEnnemis = new ArrayList<>();
    }

    /**
     * charge les enemis
     * @param chemin
     * @return
     */
    @Override
    public List<List<Entite>> charge(String chemin) {
        List<Entite> niveau = new ArrayList<>();
        lesEnnemis.add(niveau);

        niveau = new ArrayList<>();
        niveau.add(new Ennemi(
                new Position2D(384, 1152),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                new ComportementMarche(lesNiveau.get(0)),
                5.5,
                1,
                3));
        lesEnnemis.add(niveau);

        niveau = new ArrayList<>();
        niveau.add(new Ennemi(
                new Position2D(896, 6208),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                new ComportementMarche(lesNiveau.get(0)),
                5.5,
                1,
                3));
        niveau.add(new Ennemi(
                new Position2D(1152, 3648),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                new ComportementMarche(lesNiveau.get(0)),
                5.5,
                1,
                3));

        lesEnnemis.add(niveau);

        niveau = new ArrayList<>();
        niveau.add(new Ennemi(
                new Position2D(2752, 512),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                new ComportementMarche(lesNiveau.get(0)),
                5.5,
                1,
                3));
        niveau.add(new Ennemi(
                new Position2D(2112, 1472),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                new ComportementMarche(lesNiveau.get(0)),
                5.5,
                1,
                3));
        lesEnnemis.add(niveau);
        return lesEnnemis;
    }
}
