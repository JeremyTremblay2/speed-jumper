package fr.iut.speedjumper.adaptateur;

import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import fr.iut.speedjumper.vues.MenuPrincipal;
import fr.iut.speedjumper.R;
import fr.iut.speedjumper.fragment.FragmentChoixDifficulte;

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
            ((MenuPrincipal)leBouton.getContext()).setNiveauChoisi(niveauCourant);
            ((MenuPrincipal)leBouton.getContext()).getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentMenu, FragmentChoixDifficulte.class, null)
                    .commit();
        });
    }
}