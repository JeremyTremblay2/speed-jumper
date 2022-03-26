package fr.iut.speedjumper.vues;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.jeu.Jeu;

public class VueMenuJouer extends AppCompatActivity {
    private OrientationEventListener orientationEventListener;
    private Jeu jeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_jouer);

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
        try {
            getAssets().open("raw/cartes/carte1.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("SpeedJumper", "Avant création de la vue de tuile");
        VueNiveau niveau = new VueNiveau(this);
        setContentView(niveau);
        niveau.postInvalidate();
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
}
