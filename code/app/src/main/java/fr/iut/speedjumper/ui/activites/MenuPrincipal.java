package fr.iut.speedjumper.ui.activites;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.donnees.ChargeurReglage;
import fr.iut.speedjumper.donnees.SauvegardeReglage;
import fr.iut.speedjumper.ui.fragment.FragmentChoixNiveau;
import fr.iut.speedjumper.ui.fragment.FragmentMenu;
import fr.iut.speedjumper.ui.fragment.FragmentReglage;

public class MenuPrincipal extends AppCompatActivity {
    public static final String LES_REGLAGE = "lesEtudiants";
    private int volumeSon = 100;
    private int volumeMusique = 100;
    private String niveauChoisi =null;
    private String difficulteChoisi=null;
    private ChargeurReglage leChargeur;
    private SauvegardeReglage laSauvegarde = new SauvegardeReglage();

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

        setVolumeSon(100);
        setVolumeMusique(100);

        leChargeur = new ChargeurReglage();
        try{
            String reglage = (String) leChargeur.load(openFileInput(LES_REGLAGE));
            String[] reglageTab = reglage.split("_");
            setVolumeMusique(Integer.parseInt(reglageTab[0]));
            setVolumeSon(Integer.parseInt(reglageTab[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, FragmentMenu.class, null)
                .commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentMenu, FragmentMenu.class, null)
                    .commit();
        }
    }

    public void goToJouer(View view) {
        Intent intent = new Intent(this, MenuJouer.class);
        startActivity(intent);
    }
    public void gotoReglage(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, FragmentReglage.class, null)
                .commit();
    }

    public void gotoMenu(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, FragmentMenu.class, null)
                .commit();
    }

    public void goToChoixNiveau(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentMenu, FragmentChoixNiveau.class, null)
                .commit();
    }

    public void quitter(View view) {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            laSauvegarde.save(openFileOutput(LES_REGLAGE, MODE_PRIVATE), String.valueOf(volumeMusique)+"_"+String.valueOf(volumeSon));
        } catch (FileNotFoundException e) {
            Log.e(getPackageName(), "Sauvegarde impossible");
        }
    }
}
