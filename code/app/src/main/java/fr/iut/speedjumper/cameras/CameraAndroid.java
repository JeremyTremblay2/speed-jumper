package fr.iut.speedjumper.cameras;

import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.monde.Carte2D;

public class CameraAndroid extends CameraCarteTuiles {
    /**
     * Constructeur de la cameraCartesTuiles
     *
     * @param carte        carte ou doit etre afficher la camera
     * @param zoneVisuelle dimension de la zone visible
     * @throws IllegalArgumentException
     */
    public CameraAndroid(Carte2D carte, Dimension zoneVisuelle) throws IllegalArgumentException {
        super(carte, zoneVisuelle);
    }
}
