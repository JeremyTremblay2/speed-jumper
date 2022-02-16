package fr.iut.speedjumper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReglageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglage);
        SeekBar Volume = (SeekBar) findViewById(R.id.VolumeBar);
        TextView affichageVolume = (TextView) findViewById(R.id.Volume);
        Volume.setProgress(0);
        affichageVolume.setText(String.valueOf(Volume.getProgress()));
        Volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                affichageVolume.setText(String.valueOf(progress));
            }
        });
    }
}
