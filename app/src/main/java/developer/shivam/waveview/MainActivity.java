package developer.shivam.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private WaveView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Initializing the wave and setting it
         *  to the contentView of this activity
         */
        view = new WaveView(mContext);
        setContentView(view);
    }
}
