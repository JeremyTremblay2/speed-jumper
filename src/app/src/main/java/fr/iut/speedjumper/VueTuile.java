package fr.iut.speedjumper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.Locale;

import fr.iut.speedjumper.logique.Position2D;
import fr.iut.speedjumper.monde.Tuile;

public class VueTuile extends View {
    private Bitmap image;
    private Tuile tuile;

    public VueTuile(Context context, Tuile tuile) throws IllegalArgumentException {
        super(context);
        if (tuile == null) {
            throw new IllegalArgumentException("La tuille passée en paramètre ne peut pas être nulle.");
        }
        this.tuile = tuile;
        String id = String.format(Locale.getDefault(), "tuile_%d", tuile.getIdTuile());
        int tuileId = getResources().getIdentifier(id, "drawable", context.getPackageName());
        image = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), tuileId), 32, 32, false);
        Log.d("SpeedJumper", "Création tuile ID " + tuile.getIdTuile());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("SpeedJumper", "Dans draw de tuile");
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(250);
        canvas.drawBitmap(image, 0, 0, paint);
    }
}
