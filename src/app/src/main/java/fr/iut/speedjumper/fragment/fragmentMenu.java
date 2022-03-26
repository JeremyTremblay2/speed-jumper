package fr.iut.speedjumper.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.speedjumper.MenuPrincipal;
import fr.iut.speedjumper.R;

public class fragmentMenu extends Fragment {
    private MenuPrincipal activiteParente ;

    public fragmentMenu() {
        super(R.layout.fragment_menu_principal);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activiteParente = (MenuPrincipal) getContext();
    }
}
