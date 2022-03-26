package fr.iut.speedjumper.ui.activites;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.donnees.AdaptateurChargeurDeCarteTiledCSV;
import fr.iut.speedjumper.donnees.ChargeurDeJeuxDeTuilesTextuel;
import fr.iut.speedjumper.donnees.ChargeurScoreTextuel;
import fr.iut.speedjumper.donnees.CollectionRessources;
import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entrees.RecuperateurDeTouchesAndroid;
import fr.iut.speedjumper.jeu.Jeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.observateurs.Observateur;
import fr.iut.speedjumper.ui.vues.VueJeu;
import fr.iut.speedjumper.ui.vues.VueNiveau;

public class ActiviteJeu extends AppCompatActivity implements Observateur {
    private OrientationEventListener orientationEventListener;
    private TableauJeu tableauJeu;
    private CollectionRessources collectionRessources;
    private Jeu jeu;
    private View vueJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_jeu);

        orientationEventListener = new OrientationEventListener((Context)this) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (orientation >= 100 && orientation < 180) {
                    Log.d("test","droit");
                } else if (orientation >= 0 && orientation < 80) {
                    Log.d("test","gauche");
                } else {
                    Log.d("test","pas bouger");
                }
            }
        };
        orientationEventListener.enable();

        collectionRessources = new CollectionRessources(getApplicationContext());
        GestionnaireDeRessources gestionnaireDeRessources = new GestionnaireDeRessources(
                new AdaptateurChargeurDeCarteTiledCSV(","),
                new ChargeurDeJeuxDeTuilesTextuel(),
                new ChargeurScoreTextuel());
        jeu = new Jeu(new RecuperateurDeTouchesAndroid(null), gestionnaireDeRessources);
        tableauJeu = jeu.getTableauJeu();
        vueJeu = new VueJeu(this, tableauJeu);
        setContentView(vueJeu);
        jeu.attacher(this);
        jeu.lance();
    }

    @Override
    protected void onPause() {
        super.onPause();
        orientationEventListener.disable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        orientationEventListener.enable();
    }

    @Override
    public void miseAJour() {
        vueJeu.postInvalidate();
    }
}
