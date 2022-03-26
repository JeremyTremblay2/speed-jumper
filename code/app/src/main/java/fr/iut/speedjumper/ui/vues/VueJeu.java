package fr.iut.speedjumper.ui.vues;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.jeu.TableauJeu;

public class VueJeu extends ViewGroup {
    private TableauJeu tableauJeu;

    public VueJeu(Context context, TableauJeu tableauJeu) {
        super(context);
        initialisation(tableauJeu);
    }

    public VueJeu(Context context, AttributeSet attrs, TableauJeu tableauJeu) {
        super(context, attrs);
        initialisation(tableauJeu);
    }

    public VueJeu(Context context, AttributeSet attrs, int defStyleAttr, TableauJeu tableauJeu) {
        super(context, attrs, defStyleAttr);
        initialisation(tableauJeu);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueJeu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes,
                  TableauJeu tableauJeu) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(tableauJeu);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
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

    private void initialisation(TableauJeu tableauJeu) throws IllegalArgumentException {
        if (tableauJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas être null");
        }
        this.tableauJeu = tableauJeu;
        addView(new VueNiveau(getContext(), tableauJeu.getNiveauCourant()));
        addView(new VueEntite(getContext(), tableauJeu.getJoueur(), R.drawable.femme));
    }
}
