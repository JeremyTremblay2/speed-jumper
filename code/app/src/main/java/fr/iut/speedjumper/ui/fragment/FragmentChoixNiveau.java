package fr.iut.speedjumper.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iut.speedjumper.adaptateur.CustomAdapter;
import fr.iut.speedjumper.ui.activites.ActiviteMenuPrincipal;
import fr.iut.speedjumper.R;

public class FragmentChoixNiveau extends Fragment {
    private ActiviteMenuPrincipal activiteParente ;
    //private List<String> listNiveau;

    public FragmentChoixNiveau() {
        super(R.layout.fragment_choix_niveau);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<String> listNiveau = new ArrayList<String>();
        listNiveau.add("Niveau 1");
        listNiveau.add("Niveau 2");
        listNiveau.add("Niveau 3");
        listNiveau.add("Niveau 4");
        listNiveau.add("Niveau 5");
        listNiveau.add("Niveau 6");
        listNiveau.add("Niveau 7");
        listNiveau.add("Niveau 8");
        listNiveau.add("Niveau 9");
        listNiveau.add("Niveau 10");
        super.onViewCreated(view, savedInstanceState);
        activiteParente= (ActiviteMenuPrincipal) getContext();
        RecyclerView laListView = view.findViewById(R.id.recycleView);
        laListView.setLayoutManager(new LinearLayoutManager(activiteParente));
        laListView.setAdapter(new CustomAdapter(listNiveau));
    }
}
