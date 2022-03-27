package fr.iut.speedjumper.ui.adaptateur;

import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import fr.iut.speedjumper.ui.activites.ActiviteMenuPrincipal;
import fr.iut.speedjumper.R;
import fr.iut.speedjumper.ui.fragment.FragmentChoixDifficulte;

public class MonViewHolder extends RecyclerView.ViewHolder {
    private final Button leBouton;
    public MonViewHolder(LinearLayout leLayout) {
        super(leLayout);
        leBouton = itemView.findViewById(R.id.buttonNiveau);
    }
    public Button getLeBouton(){
        return leBouton;
    }

    public void setNiveauCourant(String niveauCourant) {
        leBouton.setOnClickListener(v -> {
            ((ActiviteMenuPrincipal)leBouton.getContext()).setNiveauChoisi(niveauCourant);
            ((ActiviteMenuPrincipal)leBouton.getContext()).getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentMenu, FragmentChoixDifficulte.class, null)
                    .commit();
        });
    }
}