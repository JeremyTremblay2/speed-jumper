package fr.iut.speedjumper.ui.vues;

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

    public VueTuile(Context context, Tuile tuile, Bitmap image) throws IllegalArgumentException {
        super(context);
        initialisation(tuile, image);
    }

    public VueTuile(Context context, @Nullable AttributeSet attrs, Tuile tuile, Bitmap image)
            throws IllegalArgumentException {
        super(context, attrs);
        initialisation(tuile, image);
    }

    public VueTuile(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Tuile tuile,
                    Bitmap image) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        initialisation(tuile, image);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueTuile(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes,
                    Tuile tuile, Bitmap image) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(tuile, image);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(image, 0, 0, paint);
    }

    private void initialisation(Tuile tuile, Bitmap image) throws IllegalArgumentException {
        if (tuile == null) {
            throw new IllegalArgumentException("La tuille passée en paramètre ne peut pas être nulle.");
        }
        if (image == null) {
            throw new IllegalArgumentException("L'image passée en paramètre ne peut pas être nulle.");
        }
        this.tuile = tuile;
        this.image = image;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(250);
    }
}
