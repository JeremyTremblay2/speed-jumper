package fr.iut.speedjumper.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.speedjumper.ui.activites.ActiviteMenuPrincipal;
import fr.iut.speedjumper.R;

public class FragmentChoixDifficulte extends Fragment {
    private ActiviteMenuPrincipal activiteParente ;

    public FragmentChoixDifficulte() {
        super(R.layout.fragment_choix_difficulte);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activiteParente = (ActiviteMenuPrincipal) getContext();

        TextView Niveau = view.findViewById(R.id.niveauChoisi);
        Niveau.setText(activiteParente.getNiveauChoisi());

        Button difficulteNormal = view.findViewById(R.id.difficulteNormal);
        difficulteNormal.setOnClickListener(view1 -> {
            activiteParente.setDifficulteChoisi((String) difficulteNormal.getText());
            activiteParente.goToJouer(view1);
        });
        Button difficulteSpeed = view.findViewById(R.id.difficulteSpeed);
        difficulteSpeed.setOnClickListener(view13 -> {
            activiteParente.setDifficulteChoisi((String) difficulteSpeed.getText());
            activiteParente.goToJouer(view13);
        });
        Button difficulteOmbre = view.findViewById(R.id.difficulteOmbre);
        difficulteOmbre.setOnClickListener(view12 -> {
            activiteParente.setDifficulteChoisi((String) difficulteOmbre.getText());
            activiteParente.goToJouer(view12);
        });
    }
}

