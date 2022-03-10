package fr.iut.speedjumper.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.iut.speedjumper.MenuPrincipal;
import fr.iut.speedjumper.R;

public class viewHolder extends RecyclerView.ViewHolder {
    private final Button leBouton;
    public viewHolder(LinearLayout leLayout) {
        super(leLayout);
        leBouton = itemView.findViewById(R.id.buttonNiveau);
    }
    public Button getLeBouton(){
        return leBouton;
    }

    public void setNiveauCourant(String niveauCourant) {
        leBouton.setOnClickListener(v -> {
            ((MenuPrincipal)leBouton.getContext()).setEtudiantEnCours(niveauCourant);
        });
    }
}