package fr.iut.speedjumper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;

import androidx.appcompat.app.AppCompatActivity;

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
