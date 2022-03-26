package fr.iut.speedjumper.ui.vues;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import fr.iut.speedjumper.monde.Carte2D;
import fr.iut.speedjumper.monde.Tuile;

public class VueCarte extends ViewGroup {
    private List<VueTuile> lesTuiles;
    private Carte2D carteCourante;

    public VueCarte(Context context, Carte2D carte)
            throws IllegalArgumentException {
        super(context);
        initialisation(carte);
    }

    public VueCarte(Context context, @Nullable AttributeSet attrs, Carte2D carte)
            throws IllegalArgumentException {
        super(context, attrs);
        initialisation(carte);
    }

    public VueCarte(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Carte2D carte)
            throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        initialisation(carte);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueCarte(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes,
            Carte2D carte) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(carte);
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b)
            throws IllegalArgumentException {
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            int y = i / (int) carteCourante.getDimensionCarte().getLargeur();
            int x = i % ((int) carteCourante.getDimensionCarte().getLargeur());
                Tuile tuile = null;
                try {
                    tuile = carteCourante.getTuile(y, x);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(e);
                }
                View child = this.getChildAt(i);
                child.layout((int) (x * tuile.getDimension().getLargeur()),
                        (int) (y * tuile.getDimension().getHauteur()),
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

    private void initialisation(Carte2D carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être nulle.");
        }
        carteCourante = carte;
        lesTuiles = new ArrayList<>();

        Tuile[][] vueCarte = carteCourante.getLesTuiles();
        for (int y = 0; y < vueCarte.length; y++) {
            for (int x = 0; x < vueCarte[y].length; x++) {
                addView(new VueTuile(getContext(), vueCarte[y][x]));
            }
        }
    }
}
