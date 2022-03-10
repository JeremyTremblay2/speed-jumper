package fr.iut.speedjumper;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.speedjumper.fragment.fragmentChoixDifficulte;
import fr.iut.speedjumper.fragment.fragmentChoixNiveau;
import fr.iut.speedjumper.fragment.fragmentMenu;
import fr.iut.speedjumper.fragment.fragmentReglage;

public class MenuPrincipal extends AppCompatActivity {

    private int volumeSon = 100;
    private int volumeMusique = 100;
    private String niveauChoisi;
    private String difficulteChoisi;

    public int getVolumeMusique() {
        return volumeMusique;
    }
    public int getVolumeSon() {
        return volumeSon;
    }
    public String getNiveauChoisi() {
        return niveauChoisi;
    }
    public String getDifficulteChoisi() {
        return difficulteChoisi;
    }

    public void setVolumeMusique(int volumeMusique) {
        this.volumeMusique = volumeMusique;
    }
    public void setVolumeSon(int volumeSon) {
        this.volumeSon = volumeSon;
    }
    public void setNiveauChoisi(String niveauChoisi) {
        this.niveauChoisi = niveauChoisi;
    }
    public void setDifficulteChoisi(String difficulteChoisi) {
        this.difficulteChoisi = difficulteChoisi;
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

    public void goToJouer(View view) {
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

    public void goToChoixNiveau(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, fragmentChoixNiveau.class, null)
                .commit();
    }
    public void goToChoixDifficulte(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, fragmentChoixDifficulte.class, null)
                .commit();
    }

    public void quitter(View view) {
        finish();
    }

    public void setEtudiantEnCours(String niveauChoisi) {
        if (this.niveauChoisi != niveauChoisi) {
            if (niveauChoisi != null){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentMenu,fragmentChoixNiveau.class,null)
                            .setReorderingAllowed(true)
                            .addToBackStack("Liste des étudiants")
                            .commit();
            }
        }
        this.niveauChoisi = niveauChoisi;

    }


}
