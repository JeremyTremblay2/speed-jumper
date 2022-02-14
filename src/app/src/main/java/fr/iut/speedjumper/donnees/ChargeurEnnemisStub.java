package fr.iut.speedjumper.donnees;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.comportement.ComportementMarcheAvecChute;
import fr.iut.speedjumper.entites.Ennemi;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.logique.Rectangle;
import fr.iut.speedjumper.monde.Niveau;

/**
 * Classe pour charger des enemis
 */
public class ChargeurEnnemisStub implements ChargeurEnnemis {
    private List<List<Entite>> lesEnnemis;
    private TableauJeu tableauJeu;

    public ChargeurEnnemisStub(TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas être null.");
        }
        this.tableauJeu = tableauJeu;
        lesEnnemis = new ArrayList<>();
    }

    @Override
    public List<List<Entite>> charge(String chemin) {
        List<Entite> niveau = new ArrayList<>();
        lesEnnemis.add(niveau);

        niveau = new ArrayList<>();
        niveau.add(new Ennemi(
                new Position2D(384, 1152),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                5.5,
                1,
                3,
                new ComportementMarcheAvecChute(tableauJeu)));
        lesEnnemis.add(niveau);

        niveau = new ArrayList<>();
        niveau.add(new Ennemi(
                new Position2D(896, 6208),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                5.5,
                1,
                3,
                new ComportementMarcheAvecChute(tableauJeu)));

        niveau.add(new Ennemi(
                new Position2D(1152, 3648),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                5.5,
                1,
                3,
                new ComportementMarcheAvecChute(tableauJeu)));

        lesEnnemis.add(niveau);

        niveau = new ArrayList<>();
        niveau.add(new Ennemi(
                new Position2D(2752, 512),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                5.5,
                1,
                3,
                new ComportementMarcheAvecChute(tableauJeu)));

        niveau.add(new Ennemi(
                new Position2D(2112, 1472),
                new Rectangle(new Position2D(20, 50), new Dimension(60, 60)),
                new Dimension(100, 100),
                5.5,
                1,
                3,
                new ComportementMarcheAvecChute(tableauJeu)));
        lesEnnemis.add(niveau);
        return lesEnnemis;
    }
}
