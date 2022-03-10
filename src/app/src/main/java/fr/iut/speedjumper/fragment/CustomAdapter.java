package fr.iut.speedjumper.fragment;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.iut.speedjumper.MenuJouer;
import fr.iut.speedjumper.R;

public class CustomAdapter extends RecyclerView.Adapter {

    private List<String> listNiveau;

    public CustomAdapter(List<String> dataSet) {
        this.listNiveau = dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout leLayout =(LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cellule_niveau, viewGroup, false);
        return new viewHolder(leLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        String etudiantCourant = listNiveau.get(position);
        ((viewHolder)holder).setNiveauCourant(etudiantCourant);
        ((viewHolder)holder).getLeBouton().setText(etudiantCourant);
    }


    @Override
    public int getItemCount() {
        return listNiveau.size();
    }


}
