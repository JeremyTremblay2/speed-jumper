package fr.iut.speedjumper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import fr.iut.speedjumper.jeu.GestionnaireDeJeu;
import fr.iut.speedjumper.logique.Dimension;
import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.monde.Tuile;

public class CarteComplete extends ViewGroup {
    private GestionnaireDeJeu gestionnaireDeJeu;
    private Paint textPaint;
    private float width, height;
    private View vue;


    public CarteComplete(Context context) {
        super(context);
        init(context);
    }

    public CarteComplete(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CarteComplete(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarteComplete(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
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

    private void init(Context context) {
        Log.d("SpeedJumper", "Dans init de carte, avant addView");
        vue = new VueTuile(context, new Tuile(null, new Dimension(32, 32)));
        vue.postInvalidate();
        addView(vue);
        Log.d("SpeedJumper", "Dans init de carte, après addView");
        vue.postInvalidate();

        /*gestionnaireDeJeu = new GestionnaireDeJeu(
                new RecuperateurDeTouchesAndroid(this),
                new GestionnaireDeRessources(
                    new AdaptateurChargeurDeCarteTiledCSV(","),
                    new ChargeurDeJeuxDeTuilesTextuel(),
                    new ChargeurScoreTextuel()
                )
        );
        gestionnaireDeJeu.lance();*/
    }

    /*private void init(Context context) {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(12);
        VueTuile tuile = new VueTuile(getContext(), new Tuile(null, new Dimension(20, 20)), new Position2D(20, 10));
        addView(tuile);
        tuile = new VueTuile(getContext(), new Tuile(null, new Dimension(20, 20)), new Position2D(20, 10));
        addView(tuile);
        tuile = new VueTuile(getContext(), new Tuile(null, new Dimension(20, 20)), new Position2D(20, 10));
        addView(tuile);
        tuile = new VueTuile(getContext(), new Tuile(null, new Dimension(20, 20)), new Position2D(20, 10));
        addView(tuile);

        desiredWidth = getContext().getResources().getDimension(R.dimen.item_width);
        height = getContext().getResources().getDimension(R.dimen.item_height);
    }*/
}
