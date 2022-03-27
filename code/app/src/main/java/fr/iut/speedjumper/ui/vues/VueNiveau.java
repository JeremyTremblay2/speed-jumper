package fr.iut.speedjumper.ui.vues;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import fr.iut.speedjumper.R;
import fr.iut.speedjumper.entites.Entite;
import fr.iut.speedjumper.entites.PersonnageJouable;
import fr.iut.speedjumper.monde.Niveau;

public class VueNiveau extends ViewGroup {
    private Niveau niveau;

    public VueNiveau(Context context, Niveau niveau)
            throws IllegalArgumentException {
        super(context);
        initialisation(niveau);
    }

    public VueNiveau(Context context, AttributeSet attrs, Niveau niveau)
            throws IllegalArgumentException {
        super(context, attrs);
        initialisation(niveau);
    }

    public VueNiveau(Context context, AttributeSet attrs, int defStyleAttr, Niveau niveau)
            throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        initialisation(niveau);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueNiveau(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes,
                     Niveau niveau) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(niveau);
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
            child.postInvalidate();
        }
    }

    private void initialisation(Niveau niveau) {
        if (niveau == null) {
            throw new NullPointerException("Le niveau ou passé en paramètre ne peut pasêtre null.");
        }
        this.niveau = niveau;

        addView(new VueCarte(getContext(), niveau.getCarte()));
        for (Entite entite : niveau.getLesEntites()) {
            addView(new VueEntite(getContext(), entite, R.drawable.slime));
        }
    }
}
