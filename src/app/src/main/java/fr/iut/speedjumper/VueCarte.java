package fr.iut.speedjumper;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.donnees.AdaptateurChargeurDeCarteTiledCSV;
import fr.iut.speedjumper.donnees.ChargeurDeJeuxDeTuilesTextuel;
import fr.iut.speedjumper.donnees.ChargeurScoreTextuel;
import fr.iut.speedjumper.donnees.CollectionRessources;
import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entrees.RecuperateurDeTouchesAndroid;
import fr.iut.speedjumper.jeu.GestionnaireDeJeu;
import fr.iut.speedjumper.jeu.Jeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.monde.Carte2D;
import fr.iut.speedjumper.monde.Tuile;

public class VueCarte extends ViewGroup {
    private List<VueTuile> lesTuiles;
    private View vue;

    private Jeu jeu;
    private TableauJeu tableauJeu;
    private Carte2D carteCourante;

    public VueCarte(Context context, Jeu jeu)
            throws IllegalArgumentException {
        super(context);
        initialisation(jeu);
    }

    public VueCarte(Context context, @Nullable AttributeSet attrs, Jeu jeu)
            throws IllegalArgumentException {
        super(context, attrs);
        initialisation(jeu);
    }

    public VueCarte(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Jeu jeu)
            throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        initialisation(jeu);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueCarte(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes,
                    Jeu jeu) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(jeu);
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b)
            throws IllegalArgumentException {
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            int hauteur = i / (int) carteCourante.getDimensionCarte().getHauteur();
            int largeur = i % (int) carteCourante.getDimensionCarte().getLargeur();
            Tuile tuile = carteCourante.getTuile(largeur, hauteur);
            View child = this.getChildAt(i);
            child.layout((int) (largeur * tuile.getDimension().getLargeur()),
                    (int) (hauteur * tuile.getDimension().getHauteur()),
                    child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
            throws IllegalArgumentException {
        int parentWidth  = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);

        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            this.measureChild(
                    child,
                    MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.EXACTLY));
        }
    }

    private void initialisation(Jeu jeu) throws IllegalArgumentException {
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        this.jeu = jeu;
        carteCourante = jeu.getTableauJeu().getNiveauCourant().getCarte();
        Log.d("SpeedJumper", "Dans init de carte, avant addView");
        lesTuiles = new ArrayList<>();

        Tuile[][] vueCarte = carteCourante.getLesTuiles();
        for (int y = 0; y < vueCarte.length; y++) {
            for (int x = 0; x < vueCarte[y].length; x++) {
                addView(new VueTuile(getContext(), vueCarte[y][x]));
            }
        }
    }
}
