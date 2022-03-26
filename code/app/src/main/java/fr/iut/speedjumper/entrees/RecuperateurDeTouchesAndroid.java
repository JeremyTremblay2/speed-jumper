package fr.iut.speedjumper.entrees;

import android.content.Context;
import android.view.View;

import java.util.List;

public class RecuperateurDeTouchesAndroid extends RecuperateurDeTouches {
    private View vue;

     public RecuperateurDeTouchesAndroid(View vue) throws IllegalArgumentException {
         /*if (vue == null) {
             throw new IllegalArgumentException("La vue passée en paramètre ne peut pas être nulle.");
         }*/
         this.vue = vue;
         initialisation();
     }

    @Override
    public List<Touche> detecte() {
        return lesTouchesPressees;
    }

    private void initialisation() {
         //vue.findViewById();
    }
}
