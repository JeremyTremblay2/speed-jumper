package fr.iut.speedjumper.vues;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Locale;

import fr.iut.speedjumper.monde.Tuile;

public class VueTuile extends View {
    private Paint paint;
    private Bitmap image;
    private Tuile tuile;

    public VueTuile(Context context, Tuile tuile) throws IllegalArgumentException {
        super(context);
        initialisation(tuile);
    }

    public VueTuile(Context context, @Nullable AttributeSet attrs, Tuile tuile)
            throws IllegalArgumentException {
        super(context, attrs);
        this.tuile = tuile;
    }

    public VueTuile(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Tuile tuile)
            throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        this.tuile = tuile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueTuile(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes,
                    Tuile tuile) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.tuile = tuile;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("SpeedJumper", "Dans draw de tuile");
        super.onDraw(canvas);
        canvas.drawBitmap(image, 0, 0, paint);
    }

    private void initialisation(Tuile tuile) throws IllegalArgumentException {
        if (tuile == null) {
            throw new IllegalArgumentException("La tuille passée en paramètre ne peut pas être nulle.");
        }
        this.tuile = tuile;
        String id = String.format(Locale.getDefault(), "tuile_%d", tuile.getIdTuile());
        int tuileId = getResources().getIdentifier(id, "drawable", getContext().getPackageName());
        Log.d("SpeedJumper", "Création tuile ID " + id);
        Log.d("SpeedJumper", "Contexte : " + getContext());
        image = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getContext().getResources(), tuileId), (int) tuile.getDimension().getLargeur(),
                (int) tuile.getDimension().getHauteur(), false);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(250);
    }
}
