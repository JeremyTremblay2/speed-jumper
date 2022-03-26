package fr.iut.speedjumper.vues;

import android.content.Context;
import android.content.Entity;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.donnees.AdaptateurChargeurDeCarteTiledCSV;
import fr.iut.speedjumper.donnees.ChargeurDeJeuxDeTuilesTextuel;
import fr.iut.speedjumper.donnees.ChargeurScoreTextuel;
import fr.iut.speedjumper.donnees.CollectionRessources;
import fr.iut.speedjumper.donnees.GestionnaireDeRessources;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.entrees.RecuperateurDeTouchesAndroid;
import fr.iut.speedjumper.jeu.Jeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.monde.Carte2D;
import fr.iut.speedjumper.monde.Niveau;

public class VueNiveau extends ViewGroup {
    private Jeu jeu;
    private TableauJeu tableauJeu;
    private Niveau niveau;

    public VueNiveau(Context context) {
        super(context);
        initialisation();
    }

    public VueNiveau(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialisation();
    }

    public VueNiveau(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialisation();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueNiveau(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation();
    }

    @Override
    protected void onLayout(boolean b, int l, int t, int r, int bottom) {
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
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

    private void initialisation() {
        CollectionRessources collectionRessources = new CollectionRessources(getContext());
        GestionnaireDeRessources gestionnaireDeRessources = new GestionnaireDeRessources(
                new AdaptateurChargeurDeCarteTiledCSV(","),
                new ChargeurDeJeuxDeTuilesTextuel(),
                new ChargeurScoreTextuel());
        jeu = new Jeu(new RecuperateurDeTouchesAndroid(this), gestionnaireDeRessources);
        tableauJeu = jeu.getTableauJeu();
        niveau = tableauJeu.getNiveauCourant();

        addView(new VueCarte(getContext(), niveau.getCarte()));
        for (Entite entite : niveau.getLesEntites()) {
            addView(new VueJoueur(getContext(), entite, R.drawable.slime));
        }
        addView(new VueJoueur(getContext(), tableauJeu.getJoueur(), R.drawable.femme));
    }
}
