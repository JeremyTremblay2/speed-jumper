package fr.iut.speedjumper.ui.activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.donnees.AdaptateurChargeurDeCarteTiledCSV;
import fr.iut.speedjumper.donnees.ChargeurDeJeuxDeTuilesTextuel;
import fr.iut.speedjumper.donnees.ChargeurScoreTextuel;
import fr.iut.speedjumper.donnees.CollectionRessources;
import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entrees.RecuperateurDeTouchesAndroid;
import fr.iut.speedjumper.jeu.GestionnaireDeJeu;
import fr.iut.speedjumper.jeu.Jeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.monde.Tuile;
import fr.iut.speedjumper.observateurs.Observateur;
import fr.iut.speedjumper.ui.vues.VueJeu;

public class ActiviteJeu extends AppCompatActivity implements Observateur {
    private TableauJeu tableauJeu;
    private CollectionRessources collectionRessources;
    private GestionnaireDeJeu gestionnaireDeJeu;
    private View vueJeu;
    private int numeroNiveau;
    private Toast messageToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        numeroNiveau = getIntent().getExtras().getInt(ActiviteMenuPrincipal.NUMERO_NIVEAU);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_jeu_complete);
        FrameLayout parent = findViewById(R.id.parent);
        View vueBoutons = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vue_controles_jeu, null);
        Tuile.resetTuiles();
        collectionRessources = new CollectionRessources(getApplicationContext());
        GestionnaireDeRessources gestionnaireDeRessources = new GestionnaireDeRessources(
                new AdaptateurChargeurDeCarteTiledCSV(","),
                new ChargeurDeJeuxDeTuilesTextuel(),
                new ChargeurScoreTextuel());

        // Création du modèle.
        gestionnaireDeJeu = new GestionnaireDeJeu(new RecuperateurDeTouchesAndroid(vueBoutons),
                gestionnaireDeRessources);
        gestionnaireDeJeu.changerNiveau(numeroNiveau % 3);
        tableauJeu = gestionnaireDeJeu.getTableauJeu();
        vueJeu = new VueJeu(this, tableauJeu);
        parent.addView(vueJeu);
        parent.addView(vueBoutons);
        // Abonnement pour notification et mise à jour de la vue.
        gestionnaireDeJeu.attacher(this);
        if (!gestionnaireDeJeu.isLance()) {
            gestionnaireDeJeu.lancerJeu();
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
        if (messageToast != null) {
            messageToast.cancel();
        }
        gestionnaireDeJeu.fermerJeu();
    }

    @SuppressLint("RtlHardcoded")
    @Override
    protected void onPause() {
        super.onPause();
        gestionnaireDeJeu.fermerJeu();
        messageToast = Toast.makeText(getApplicationContext(), "Jeu en pause !",
                Toast.LENGTH_LONG);
        messageToast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
        messageToast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void miseAJour() {
        runOnUiThread(() -> {
            vueJeu.postInvalidate();
            vueJeu.requestLayout();
        });
    }
}
