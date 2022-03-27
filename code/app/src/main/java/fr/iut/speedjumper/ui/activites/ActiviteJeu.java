package fr.iut.speedjumper.ui.activites;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
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
import fr.iut.speedjumper.monde.Tuile;
import fr.iut.speedjumper.observateurs.Observateur;
import fr.iut.speedjumper.ui.vues.VueJeu;

public class ActiviteJeu extends AppCompatActivity implements Observateur {
    private OrientationEventListener orientationEventListener;
    private TableauJeu tableauJeu;
    private CollectionRessources collectionRessources;
    private Jeu jeu;
    private View vueJeu;
    private int numeroNiveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        numeroNiveau = (int) getIntent().getExtras().getInt(ActiviteMenuPrincipal.NUMERO_NIVEAU);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_jeu_complete);
        FrameLayout parent = findViewById(R.id.parent);

        /*orientationEventListener = new OrientationEventListener((Context)this) {
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
        orientationEventListener.enable();*/
        View vueBoutons = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vue_controles_jeu, null);

        Tuile.resetTuiles();
        collectionRessources = new CollectionRessources(getApplicationContext());
        GestionnaireDeRessources gestionnaireDeRessources = new GestionnaireDeRessources(
                new AdaptateurChargeurDeCarteTiledCSV(","),
                new ChargeurDeJeuxDeTuilesTextuel(),
                new ChargeurScoreTextuel());
        jeu = new Jeu(new RecuperateurDeTouchesAndroid(vueBoutons), gestionnaireDeRessources);
        Log.d("SpeedJumper", "NUMERO NIVEAU : " + numeroNiveau);
        jeu.changerNiveau(numeroNiveau % 3);
        tableauJeu = jeu.getTableauJeu();
        vueJeu = new VueJeu(this, tableauJeu);
        parent.addView(vueJeu);
        parent.addView(vueBoutons);
        jeu.attacher(this);
        if (!jeu.isLance()) {
            jeu.lancerJeu();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ActiviteMenuPrincipal.NUMERO_NIVEAU, numeroNiveau);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numeroNiveau = (Integer) savedInstanceState.get(ActiviteMenuPrincipal.NUMERO_NIVEAU);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jeu.arreterJeu();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //orientationEventListener.disable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //orientationEventListener.enable();
    }

    @Override
    public void miseAJour() {
        runOnUiThread(() -> {
            vueJeu.postInvalidate();
            vueJeu.requestLayout();
        });
    }
}
