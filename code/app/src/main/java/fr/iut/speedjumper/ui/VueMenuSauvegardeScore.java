package fr.iut.speedjumper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.ui.activites.MenuJouer;

public class VueMenuSauvegardeScore extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        findViewById(R.id.bouton_jouer).setOnClickListener(this::naviguerVersFenetreJouer);
    }

    private void naviguerVersFenetreJouer(View view) {
        Intent intent = new Intent(this, MenuJouer.class);
        startActivity(intent);
    }
}
