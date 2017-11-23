package lock.war3.pub.areyouok.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import lock.war3.pub.areyouok.R;

/**
 * Created by tubro on 2017/11/22.
 */

public class CaihongView2 extends View {
    private int padding;
    private Paint mPaint;
    private Paint mPaint2;
    private Paint mCirclePaint;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;
    private int mStrokeWidth = 4;
    private boolean flag;
    private Path path;
    private Path path2;

    public CaihongView2(Context context) {
        super(context);
        init(context);
    }

    public CaihongView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mPaint.setStrokeWidth(1);
        mPaint.setColor(getContext().getResources().getColor(R.color.text3));

        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);//取消锯齿
        mPaint2.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mPaint2.setStrokeWidth(1);
        mPaint2.setColor(getContext().getResources().getColor(R.color.caihong_color));

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);//取消锯齿
        mCirclePaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setColor(getContext().getResources().getColor(R.color.caihong_color));
        mPathMeasure = new PathMeasure();
        mDst = new Path();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
//                Log.i("==============", "========");
                invalidate();
            }
        });
        valueAnimator.setDuration(1500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (padding == 0) {
            padding = getWidth() / 3;
            RectF oval = new RectF(padding, 0,
                    getWidth() - padding, getHeight());
            path = new Path();
            path2 = new Path();
            path.addOval(oval, Path.Direction.CW);
            RectF outerOval = new RectF(padding, 0,
                    getWidth() - padding+20, getHeight()+20);
            path2.addOval(outerOval, Path.Direction.CW);
//            path.setFillType(Path.FillType.EVEN_ODD);
            mPathMeasure.setPath(path, true);
            mLength = mPathMeasure.getLength();
        }
        // 重置Path
        mDst.reset();
        // 硬件加速的BUG
        mDst.lineTo(0, 0);
        // 计算开始点和结束点
        float stop = mLength * mAnimatorValue;
        //// TODO: 2017/11/22 why?????
        float start = (float) (stop - ((0.8 - Math.abs(mAnimatorValue - 0.8)) * mLength));
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
//        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.rotate(30, getWidth() / 2, getHeight() / 2);

//        canvas.rotate(30, getWidth() / 2, getHeight() / 2);
        canvas.drawPath(path2, mPaint2);
        canvas.drawPath(path, mPaint);

//        canvas.rotate(120, getWidth() / 2, getHeight() / 2);
//        canvas.drawPath(path, mPaint);
//        canvas.rotate(120, getWidth() / 2, getHeight() / 2);
//        canvas.drawPath(path, mPaint);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, AutoUtils.getPercentWidthSize(46), mCirclePaint);
    }
}
