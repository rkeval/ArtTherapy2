package android.csulb.edu.arttherapy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.path;

/**
 * Created by Keval on 21-03-2017.
 */

public class DrawableView extends View {

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    public int width;
    public  int height;
    private Bitmap mBitmap;
    private Canvas  mCanvas;
    private Paint   mBitmapPaint;
    Context context;

    private Paint paint = new Paint() ;
    private Path path = new Path();

    public DrawableView(Context context) {
        super(context);
    }
    public DrawableView(Context context, AttributeSet attr){
        super(context,attr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(14f);
        canvas.drawPath(path,paint);
    }

    private void touch_start(float x, float y) {
//        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
//
//            path.reset();
            //           path.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        path.lineTo(mX, mY);

        // commit the path to our offscreen
        mCanvas.drawPath(path,  paint);
        // kill this so we don't double draw
        //      path.reset();
    }

    @Override

    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    public void clearCanvas() {

        path.reset();

        invalidate();

    }
}
