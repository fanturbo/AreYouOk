package lock.war3.pub.areyouok.widget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import lock.war3.pub.areyouok.R;

/**
 * Created by tubro on mMaxStrokeWidth17/11/22.
 */

public class CaihongView5 extends View {
    private int padding;
    private Paint mPaint;
    private Paint mCirclePaint;
    private float mAnimatorValue;
    private int mStrokeWidth = 1;
    private Path path;
    private RectF currentPoint;
    private int mMaxStrokeWidth = 14;

    public CaihongView5(Context context) {
        super(context);
        init(context);
    }

    public CaihongView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(getContext().getResources().getColor(R.color.caihong_color));

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);//取消锯齿
        mCirclePaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setColor(getContext().getResources().getColor(R.color.caihong_color));
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.lineTo(0, 0);
        padding = getWidth() / 3;
        RectF oval = new RectF(padding, 0,
                getWidth() - padding, getHeight());
        path.addOval(oval, Path.Direction.CW);

        if (currentPoint == null) {
            currentPoint = new RectF(padding - mMaxStrokeWidth, 0 - mMaxStrokeWidth,
                    getWidth() - padding, getHeight());
            drawOval(canvas);
            startAnimation();
        } else {
            drawOval(canvas);
        }
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, AutoUtils.getPercentWidthSize(46), mCirclePaint);
    }

    private void drawOval(Canvas canvas) {
        path.addOval(currentPoint, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.rotate(30, getWidth() / 2, getHeight() / 2);
        canvas.drawPath(path, mPaint);
        canvas.rotate(120, getWidth() / 2, getHeight() / 2);
        canvas.drawPath(path, mPaint);
        canvas.rotate(120, getWidth() / 2, getHeight() / 2);
        canvas.drawPath(path, mPaint);
    }

    private void startAnimation() {
        int left = padding;
        int w = getWidth() - padding;
        int h = getHeight();
        RectF leftCenterRect = new RectF(left - mMaxStrokeWidth, 0, w, h);
        RectF topRect = new RectF(left - mMaxStrokeWidth, 0 - mMaxStrokeWidth, w, h);
        RectF topCenterRect = new RectF(left, 0 - mMaxStrokeWidth, w, h);
        RectF rightRect = new RectF(left, 0 - mMaxStrokeWidth, w + mMaxStrokeWidth, h);
        RectF rightCenterRect = new RectF(left, 0, w + mMaxStrokeWidth, h);
        RectF bottomRect = new RectF(left, 0, w + mMaxStrokeWidth, h + mMaxStrokeWidth);
        RectF bottomCenterRect = new RectF(left, 0, w, h + mMaxStrokeWidth);
        RectF leftRect = new RectF(left - mMaxStrokeWidth, 0, w, h + mMaxStrokeWidth);

        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), leftCenterRect, topRect, topCenterRect, rightRect,
                rightCenterRect, bottomRect, bottomCenterRect, leftRect);
//        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), leftCenterRect, topRect, topCenterRect);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (RectF) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setRepeatCount(ValueAnimator.INFINITE);
//        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setDuration(1200);
        anim.start();
    }

    class PointEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            RectF startPoint = (RectF) startValue;
            RectF endPoint = (RectF) endValue;
            float top = startPoint.top + fraction * (endPoint.top - startPoint.top);
            float bottom = startPoint.bottom + fraction * (endPoint.bottom - startPoint.bottom);
            float left = startPoint.left + fraction * (endPoint.left - startPoint.left);
            float right = startPoint.right + fraction * (endPoint.right - startPoint.right);
            RectF rectF = new RectF(left, top, right, bottom);
            return rectF;
        }
    }
}
