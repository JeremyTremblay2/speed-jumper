package fr.iut.speedjumper.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.speedjumper.MenuPrincipal;
import fr.iut.speedjumper.R;

public class fragmentChoixNiveau extends Fragment {
    private MenuPrincipal activiteParente ;

    public fragmentChoixNiveau() {
        super(R.layout.fragment_choix_niveau);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activiteParente= (MenuPrincipal) getContext();
    }
}
