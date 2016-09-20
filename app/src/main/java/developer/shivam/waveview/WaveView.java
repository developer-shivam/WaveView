package developer.shivam.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {

    private Handler handler1;
    private Handler handler2;
    private Handler handler3;
    private Context mContext = null;
    private int width = 0;
    float x1, x2, x3;
    float y1, y2, y3;
    float oldX1, oldX2, oldX3;
    float oldY1, oldY2, oldY3;
    private Paint lineColor;
    private Paint secondLineColor;
    private Paint thirdLineColor;
    int frequency = 90;
    int amplitude = 80;
    private float shift = 0;
    private float movement2 = 0;
    private float movement3 = 0;
    private int quadrant;
    long time = System.currentTimeMillis();
    Path mPath = new Path();
    Path secondPath = new Path();

    public WaveView(Context context) {
        super(context);
        init(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        lineColor = new Paint();
        lineColor.setAntiAlias(true);
        lineColor.setStrokeWidth(2);
        lineColor.setColor(Color.parseColor("#F06292"));

        secondLineColor = new Paint();
        secondLineColor.setAntiAlias(true);
        secondLineColor.setStrokeWidth(2);
        secondLineColor.setColor(Color.parseColor("#E91E63"));

        handler1 = new Handler();
        handler1.postDelayed(new CustomRunnableOne(), 16);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#F8BBD0"));
        quadrant = getHeight()/3;

        width = canvas.getWidth();

        mPath.moveTo(0, getHeight());
        secondPath.moveTo(0, getHeight());
        mPath.lineTo(0, quadrant);
        secondPath.lineTo(0, quadrant*2);

        for (int i = 0; i < width; i++) {
            x1 = (float) i;

            y1 = quadrant + amplitude * (float) Math.sin(((i) * Math.PI / frequency) + shift);
            y2 = quadrant * 2 + amplitude * (float) Math.sin(((i) * Math.PI / frequency) + shift);

            mPath.lineTo(x1, y1);
            secondPath.lineTo(x1, y2);
        }
        mPath.lineTo(getWidth(), getHeight());
        secondPath.lineTo(getWidth(), getHeight());
        canvas.drawPath(mPath, lineColor);
        canvas.drawPath(secondPath, secondLineColor);
    }

    public void setMovementOne(float movement) {
        this.shift = movement;
        mPath.reset();
        secondPath.reset();
        invalidate();
    }


    public class CustomRunnableOne implements Runnable {

        @Override
        public void run() {
            float movement = (float) (shift + 0.2);
            setMovementOne(movement);
            handler1.postDelayed(new CustomRunnableOne(), 16);
        }
    }
}
