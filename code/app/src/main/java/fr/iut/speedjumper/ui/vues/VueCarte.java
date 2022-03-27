package fr.iut.speedjumper.ui.vues;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import fr.iut.speedjumper.monde.Carte2D;
import fr.iut.speedjumper.monde.Tuile;

public class VueCarte extends ViewGroup {
    private Map<Tuile, Bitmap> lesImages;
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
    protected void onLayout(boolean changed, int l, int t, int r, int b)
            throws IllegalArgumentException {
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            int y = i / (int) carteCourante.getDimensionCarte().getLargeur();
            int x = i % ((int) carteCourante.getDimensionCarte().getLargeur());
                Tuile tuile = null;
                tuile = carteCourante.getTuile(y, x);
                View child = this.getChildAt(i);
                child.layout((int) (x * tuile.getDimension().getLargeur()),
                        (int) (y * tuile.getDimension().getHauteur()),
                        child.getMeasuredWidth(), child.getMeasuredHeight());
                child.postInvalidate();

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
        lesImages = new HashMap<>();

        Tuile[][] vueCarte = carteCourante.getLesTuiles();
        generationImage(vueCarte);
        for (int y = 0; y < vueCarte.length; y++) {
            for (int x = 0; x < vueCarte[y].length; x++) {
                addView(new VueTuile(getContext(), vueCarte[y][x],
                        lesImages.get(vueCarte[y][x])));
            }
        }
    }

    private void generationImage(Tuile[][] vueCarte) {
        for (int y = 0; y < vueCarte.length; y++) {
            for (int x = 0; x < vueCarte[y].length; x++) {
                Tuile tuile = vueCarte[y][x];
                if (!lesImages.containsKey(tuile)) {
                    String id = String.format(Locale.getDefault(), "tuile_%d", tuile.getIdTuile());
                    int tuileId = getResources().getIdentifier(id, "drawable", getContext().getPackageName());
                    Bitmap image = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                            getContext().getResources(), tuileId), (int) tuile.getDimension().getLargeur(),
                            (int) tuile.getDimension().getHauteur(), false);
                    lesImages.put(tuile, image);
                }
            }
        }
    }
}
