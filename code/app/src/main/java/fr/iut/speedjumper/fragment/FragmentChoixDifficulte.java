package fr.iut.speedjumper.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.speedjumper.vues.MenuPrincipal;
import fr.iut.speedjumper.R;

public class FragmentChoixDifficulte extends Fragment {
    private MenuPrincipal activiteParente ;
    public FragmentChoixDifficulte() {
        super(R.layout.fragment_choix_difficulte);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activiteParente= (MenuPrincipal) getContext();

        TextView Niveau = (TextView) view.findViewById(R.id.niveauChoisi);
        Niveau.setText(activiteParente.getNiveauChoisi());

        Button difficulteNormal =(Button) view.findViewById(R.id.difficulteNormal) ;
        difficulteNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activiteParente.setDifficulteChoisi((String) difficulteNormal.getText());
                activiteParente.goToJouer(view);
            }
        });
        Button difficulteSpeed=(Button) view.findViewById(R.id.difficulteSpeed) ;
        difficulteSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activiteParente.setDifficulteChoisi((String) difficulteSpeed.getText());
                activiteParente.goToJouer(view);
            }
        });
        Button difficulteOmbre =(Button) view.findViewById(R.id.difficulteOmbre) ;
        difficulteOmbre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activiteParente.setDifficulteChoisi((String) difficulteOmbre.getText());
                activiteParente.goToJouer(view);
            }
        });
    }
}

