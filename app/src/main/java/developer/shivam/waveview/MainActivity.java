package developer.shivam.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private Context mContext = MainActivity.this;
    private WaveView view;
    private SeekBar speedSeekBar, amplitudeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (WaveView) findViewById(R.id.sample_wave_view);
        speedSeekBar = (SeekBar) findViewById(R.id.seekBarSpeed);
        speedSeekBar.setMax(10);
        speedSeekBar.setOnSeekBarChangeListener(this);
        amplitudeSeekBar = (SeekBar) findViewById(R.id.seekBarAmplitude);
        amplitudeSeekBar.setMax(10);
        amplitudeSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekBarSpeed) {
            if (fromUser) {
                Log.d("Value", String.valueOf(progress));
                view.setSpeed(progress);
            }
        } else if (seekBar.getId() == R.id.seekBarAmplitude) {
            if (fromUser) {
                view.setAmplitude(progress);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
