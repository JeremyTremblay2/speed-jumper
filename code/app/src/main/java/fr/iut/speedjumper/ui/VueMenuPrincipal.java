package fr.iut.speedjumper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.ui.activites.ActiviteJeu;

public class VueMenuPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        findViewById(R.id.bouton_jouer).setOnClickListener(this::naviguerVersFenetreJouer);
        findViewById(R.id.bouton_quitter).setOnClickListener(this::quitter);
    }

    private void naviguerVersFenetreJouer(View view) {
        Intent intent = new Intent(this, ActiviteJeu.class);
        startActivity(intent);
    }

    private void quitter(View view) {
        finish();
    }
}
