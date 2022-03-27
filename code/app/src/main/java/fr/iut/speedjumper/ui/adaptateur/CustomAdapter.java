package fr.iut.speedjumper.ui.adaptateur;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.iut.speedjumper.R;

public class CustomAdapter extends RecyclerView.Adapter {
    private List<String> listNiveau;

    public CustomAdapter(List<String> dataSet) {
        this.listNiveau = dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout leLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cellule_niveau, viewGroup, false);
        return new MonViewHolder(leLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        String niveauCourant = listNiveau.get(position);
        ((MonViewHolder)holder).setNiveauCourant(niveauCourant);
        ((MonViewHolder)holder).getLeBouton().setText(niveauCourant);
    }

    @Override
    public int getItemCount() {
        return listNiveau.size();
    }
}
