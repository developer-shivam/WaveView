package developer.shivam.library;

/**
 * This is library implementation of Wave View
 *  Wave view is structured on the Sine Wave formula
 *  The sine wave formula is :
 *
 *  y(t) = A sin(2πft + ρ) = A sin(ωt + ρ)
 *
 *  The above formula can be explained in sound terms as follows:
 *
 *  y = amplitude X sin ( 2π ( velocity of rotation in cycles per second))
 *
 *  Increasing the amplitude of the sine wave, how high the tops and bottoms of the wave go, increases the volume.
 *  Increasing or decreasing the cycle rate, how many cycles over distance/time, increases and decreases the pitch of the sound – how high or low the tone sounds.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {

    /**
     * To run animation in a infinite loop handler and runnable is used
     *  In every 16ms difference the wave is updated
     */
    private Handler handler;

    private Context mContext = null;

    /**
     * @width - Device screen width
     */
    private int width = 0;

    /**
     * x, y1, y2 are used to plot the path for wave
     */
    float x;
    float y1, y2;

    private Paint firstWaveColor;
    private Paint secondWaveColor;

    /**
     * @frequency - Then less the frequency more will be the number of
     *  waves
     */
    int frequency = 180;

    /**
     * @amplitude - Amplitude gives the height of wave
     */

    int amplitude = 80;
    private float shift = 0;

    private int quadrant;

    Path firstWavePath = new Path();
    Path secondWavePath = new Path();

    /**
     * @speed - Its the velocity of wave in x-axis
     */
    private float speed = (float) 0.5;

    public WaveView(Context context) {
        super(context);
        init(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.wave, 0, 0);
            int height = attributes.getInt(R.styleable.wave_wave_height, 8) ;
            if (height > 10) {
                amplitude = 200;
            } else {
                amplitude = height * 20;
            }
            float s = attributes.getInt(R.styleable.wave_wave_speed, 1);
            if (s > 10) {
                speed = (float) 0.25;
            } else {
                speed = s/8;
            }
        }

        mContext = context;
        firstWaveColor = new Paint();
        firstWaveColor.setAntiAlias(true);
        firstWaveColor.setStrokeWidth(2);
        firstWaveColor.setColor(Color.parseColor("#64B5F6"));

        secondWaveColor = new Paint();
        secondWaveColor.setAntiAlias(true);
        secondWaveColor.setStrokeWidth(2);
        secondWaveColor.setColor(Color.parseColor("#2196F3"));

        handler = new Handler();
        handler.postDelayed(new WaveRunnable(), 16);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#BBDEFB"));
        quadrant = getHeight()/3;
        width = canvas.getWidth();

        firstWavePath.moveTo(0, getHeight());
        secondWavePath.moveTo(0, getHeight());
        firstWavePath.lineTo(0, quadrant);
        secondWavePath.lineTo(0, quadrant*2);

        for (int i = 0; i < width + 10; i = i + 10) {
            x = (float) i;

            y1 = quadrant + amplitude * (float) Math.sin(((i + 10) * Math.PI / frequency) + shift);
            y2 = quadrant * 2 + amplitude * (float) Math.sin(((i + 10) * Math.PI / frequency) + shift);

            firstWavePath.lineTo(x, y1);
            secondWavePath.lineTo(x, y2);
        }
        firstWavePath.lineTo(getWidth(), getHeight());
        secondWavePath.lineTo(getWidth(), getHeight());
        canvas.drawPath(firstWavePath, firstWaveColor);
        canvas.drawPath(secondWavePath, secondWaveColor);
    }


    private class WaveRunnable implements Runnable {

        /**
         * This runnable helps to run animation in an infinite loop
         */
        @Override
        public void run() {
            firstWavePath.reset();
            secondWavePath.reset();
            shift = shift + speed;
            invalidate();
            handler.postDelayed(new WaveRunnable(), 16);
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed/8;
        invalidate();
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude * 20;
        invalidate();
    }
}
