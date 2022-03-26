package fr.iut.speedjumper.entrees;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import fr.iut.speedjumper.R;

public class RecuperateurDeTouchesAndroid extends RecuperateurDeTouches {
    private View vue;

     public RecuperateurDeTouchesAndroid(View vue) throws IllegalArgumentException {
         if (vue == null) {
             throw new IllegalArgumentException("La vue passée en paramètre ne peut pas être nulle.");
         }
         this.vue = vue;
         initialisation();
     }

    @Override
    public List<Touche> detecte() {
        return lesTouchesPressees;
    }

    private void initialisation() {
        ImageButton boutonGauche = vue.findViewById(R.id.bouton_fleche_gauche);
        ImageButton boutonDroit = vue.findViewById(R.id.bouton_fleche_droite);
        ImageButton boutonSaut = vue.findViewById(R.id.bouton_fleche_saut);
        genererEcouteur(boutonGauche, Touche.FLECHE_GAUCHE);
        genererEcouteur(boutonDroit, Touche.FLECHE_DROITE);
        genererEcouteur(boutonSaut, Touche.ESPACE);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void genererEcouteur(ImageButton bouton, Touche typeTouche) {
        bouton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                lesTouchesPressees.add(typeTouche);
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                lesTouchesPressees.remove(typeTouche);
            }
            System.out.println(lesTouchesPressees);
            return true;
        });
    }
}
