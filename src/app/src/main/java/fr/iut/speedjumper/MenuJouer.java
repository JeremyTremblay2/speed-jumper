package fr.iut.speedjumper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.monde.Tuile;

public class MenuJouer extends AppCompatActivity {
    OrientationEventListener orientationEventListener;

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
        new Tuile(null, new Dimension(20, 20));
        //new Tuile(null, new Dimension(20, 20));
        Log.d("SpeedJumper", "Avant création de la vue de tuile");
        CarteComplete carte = new CarteComplete(this);
        //VueTuile vueTuile = new VueTuile(getApplicationContext(), new Tuile(null, new Dimension(20, 20)));
        //setContentView(vueTuile);
        setContentView(carte);
        Log.d("SpeedJumper", "Après création de la vue de tuile");
        //vueTuile.postInvalidate();
        carte.postInvalidate();
        Log.d("SpeedJumper", "Après postInvalidate fin du cteur MenuJouer");
        //new CarteComplete(this);
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
