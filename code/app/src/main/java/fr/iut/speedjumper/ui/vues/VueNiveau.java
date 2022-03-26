package fr.iut.speedjumper.vues;

import android.content.Context;
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
import fr.iut.speedjumper.entites.PersonnageJouable;
import fr.iut.speedjumper.entrees.RecuperateurDeTouchesAndroid;
import fr.iut.speedjumper.jeu.Jeu;
import fr.iut.speedjumper.jeu.TableauJeu;
import fr.iut.speedjumper.monde.Niveau;

public class VueNiveau extends ViewGroup {
    private Niveau niveau;
    private PersonnageJouable joueur;

    public VueNiveau(Context context, Niveau niveau, PersonnageJouable joueur)
            throws IllegalArgumentException {
        super(context);
        initialisation(niveau, joueur);
    }

    public VueNiveau(Context context, AttributeSet attrs, Niveau niveau, PersonnageJouable joueur)
            throws IllegalArgumentException {
        super(context, attrs);
        initialisation(niveau, joueur);
    }

    public VueNiveau(Context context, AttributeSet attrs, int defStyleAttr, Niveau niveau,
                     PersonnageJouable joueur) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        initialisation(niveau, joueur);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueNiveau(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes,
                     Niveau niveau, PersonnageJouable joueur) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(niveau, joueur);
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

    private void initialisation(Niveau niveau, PersonnageJouable joueur) {
        if (niveau == null || joueur == null) {
            throw new NullPointerException("Le niveau ou le joueur passé en paramètre est null.");
        }
        this.joueur = joueur;
        this.niveau = niveau;

        addView(new VueCarte(getContext(), niveau.getCarte()));
        for (Entite entite : niveau.getLesEntites()) {
            addView(new VueEntite(getContext(), entite, R.drawable.slime));
        }
        addView(new VueEntite(getContext(), joueur, R.drawable.femme));
    }
}
