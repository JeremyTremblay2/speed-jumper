package com.jeremyantoine.speedjumper.launcheur;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.utilitaire.DecoupeurFX;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DecoupageTuiles extends Application {
    public static void main(String[] args) {
        DecoupeurFX decoupeurFX = new DecoupeurFX();
        List<Image> images = new ArrayList<>();
        CollectionRessources ressources = CollectionRessources.getInstance();


        for (Map.Entry<String, Dimension> paire : ressources.getLesJeuxDeTuiles().entrySet()) {
            images.addAll(decoupeurFX.decoupe(paire.getKey(),
                    (int) paire.getValue().getLargeur(),
                    (int) paire.getValue().getHauteur()));
        }

        System.out.println(images.size());
        int compteur = 0;

        for (Image image : images) {
            File outputFile = new File(System.getProperty("user.home")
                    + System.getProperty("file.separator") + "android" + System.getProperty("file.separator") + "tuile_" + compteur + ".png");
            System.out.println(outputFile);
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            compteur++;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
