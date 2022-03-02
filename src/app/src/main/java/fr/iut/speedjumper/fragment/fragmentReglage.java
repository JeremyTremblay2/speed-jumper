package fr.iut.speedjumper.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.speedjumper.MenuJouer;
import fr.iut.speedjumper.MenuPrincipal;
import fr.iut.speedjumper.R;

public class fragmentReglage extends Fragment {
    private MenuPrincipal activiteParente ;

    private boolean imageDl;

    public fragmentReglage() {
        super(R.layout.fragment_reglage);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activiteParente= (MenuPrincipal) getContext();

        view.findViewById(R.id.bouton_retour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activiteParente.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentMenu, fragmentMenu.class, null)
                        .commit();
            }
        });

        SeekBar volumeMusique=(SeekBar) view.findViewById(R.id.reglageMusique);
        TextView affichageMusique=(TextView) view.findViewById(R.id.valeurMusique);
        volumeMusique.setProgress(activiteParente.getVolumeMusique());
        affichageMusique.setText(String.valueOf(activiteParente.getVolumeMusique()));

        SeekBar volumeSon=(SeekBar)view.findViewById(R.id.reglageSon);
        TextView affichageSon=(TextView) view.findViewById(R.id.valeurSon);
        volumeSon.setProgress(activiteParente.getVolumeSon());
        affichageSon.setText(String.valueOf(activiteParente.getVolumeSon()));

        volumeMusique.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                affichageMusique.setText(String.valueOf(volumeMusique.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                affichageMusique.setText(String.valueOf(volumeMusique.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                affichageMusique.setText(String.valueOf(volumeMusique.getProgress()));
                activiteParente.setVolumeMusique(volumeMusique.getProgress());
            }
        });
        volumeSon.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                affichageSon.setText(String.valueOf(volumeSon.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                affichageSon.setText(String.valueOf(volumeSon.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                affichageSon.setText(String.valueOf(volumeSon.getProgress()));
                activiteParente.setVolumeSon(volumeSon.getProgress());
            }
        });
    }
}
