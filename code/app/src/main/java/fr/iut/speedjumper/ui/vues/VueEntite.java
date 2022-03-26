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

import fr.iut.speedjumper.entites.Entite;

public class VueEntite extends View {
    private Paint paint;
    private Entite entite;
    private Bitmap image;

    public VueEntite(Context context, Entite entite, int idRessource) {
        super(context);
        initialisation(entite, idRessource);
    }

    public VueEntite(Context context, @Nullable AttributeSet attrs, Entite entite, int idRessource)
            throws IllegalArgumentException {
        super(context, attrs);
        initialisation(entite, idRessource);
    }

    public VueEntite(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                     Entite entite, int idRessource) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        initialisation(entite, idRessource);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VueEntite(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes,
                     Entite entite, int idRessource) throws IllegalArgumentException {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialisation(entite, idRessource);
    }

    public Entite getEntite() {
        return entite;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(image, (int) entite.getPosition().getX(),
                (int) entite.getPosition().getY(), paint);
    }

    private void initialisation(Entite entite, int idRessource) throws IllegalArgumentException {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité passée en paramètre ne peut pas être nulle.");
        }
        this.entite = entite;
        image = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getContext().getResources(), idRessource), (int) entite.getDimension().getLargeur(),
                (int) entite.getDimension().getHauteur(), false);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(250);
    }
}
