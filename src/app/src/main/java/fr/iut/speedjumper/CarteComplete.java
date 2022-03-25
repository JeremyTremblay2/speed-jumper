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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            childAt.layout(left, top, left + childAt.getMeasuredWidth(), top + childAt.getMeasuredHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        vue.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());

        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) width, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }*/
    }

    private void init(Context context) {
        Log.d("SpeedJumper", "Dans init");
        vue = new VueTuile(context, new Tuile(null, new Dimension(20, 20)));
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
