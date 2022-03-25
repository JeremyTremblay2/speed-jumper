package fr.iut.speedjumper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class VueMenuJouer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_jouer);
        new CarteComplete(this);
    }
}
