package fr.iut.speedjumper.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.speedjumper.ui.activites.MenuPrincipal;
import fr.iut.speedjumper.R;

public class FragmentReglage extends Fragment {
    private MenuPrincipal activiteParente ;

    public FragmentReglage() {
        super(R.layout.fragment_reglage);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activiteParente = (MenuPrincipal) getContext();

        view.findViewById(R.id.bouton_retour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activiteParente.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentMenu, FragmentMenu.class, null)
                        .commit();
            }
        });

        SeekBar volumeMusique = view.findViewById(R.id.reglageMusique);
        TextView affichageMusique = view.findViewById(R.id.valeurMusique);
        volumeMusique.setProgress(activiteParente.getVolumeMusique());
        affichageMusique.setText(String.valueOf(activiteParente.getVolumeMusique()));

        SeekBar volumeSon = view.findViewById(R.id.reglageSon);
        TextView affichageSon = view.findViewById(R.id.valeurSon);

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