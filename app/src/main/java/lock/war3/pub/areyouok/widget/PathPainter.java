package lock.war3.pub.areyouok.widget;

/**
 * Created by tubro on 2017/11/22.
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 路径动画 PathMeasure
 * <p/>
 * Created by xuyisheng on 16/7/15.
 */
public class PathPainter extends View {

    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;
    private int mStrokeWidth = 4;
    private boolean flag;

    public PathPainter(Context context) {
        super(context);
    }

    public PathPainter(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPathMeasure = new PathMeasure();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
//        mPath.addCircle(getWidth() / 2, getHeight() / 2, 100, Path.Direction.CW);
//        mPathMeasure.setPath(mPath, true);
//        mLength = mPathMeasure.getLength();
        mDst = new Path();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    public PathPainter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.addCircle(getWidth() / 2, getHeight() / 2, 100, Path.Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();


        mDst.reset();
        // 硬件加速的BUG
        mDst.lineTo(0, 0);
        float stop = mLength * mAnimatorValue;
        float start = 0;//(float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        mPathMeasure.getSegment(start, stop, mDst, true);
        if (!flag) {
            mStrokeWidth++;
            if (mStrokeWidth == 20) {
                flag = true;
            }
        } else {
            mStrokeWidth--;
            if (mStrokeWidth == 1) {
                flag = false;
            }
        }
        mPaint.setStrokeWidth(mStrokeWidth);

        canvas.drawPath(mDst, mPaint);
    }
}
