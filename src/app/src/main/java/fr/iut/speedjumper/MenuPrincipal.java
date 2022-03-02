package fr.iut.speedjumper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.fragment.fragmentMenu;
import fr.iut.speedjumper.fragment.fragmentReglage;

public class MenuPrincipal extends AppCompatActivity {

    private int volumeSon;
    private int volumeMusique;

    public int getVolumeMusique() {
        return volumeMusique;
    }

    public int getVolumeSon() {
        return volumeSon;
    }

    public void setVolumeMusique(int volumeMusique) {
        this.volumeMusique = volumeMusique;
    }

    public void setVolumeSon(int volumeSon) {
        this.volumeSon = volumeSon;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, fragmentMenu.class, null)
                .commit();

    }

    private void naviguerVersFenetreJouer(View view) {
        Intent intent = new Intent(this, MenuJouer.class);
        startActivity(intent);
    }
    public void gotoReglage(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, fragmentReglage.class, null)
                .commit();
    }

    public void gotoMenu(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, fragmentReglage.class, null)
                .commit();
    }

    public void quitter(View view) {
        finish();
    }


}
