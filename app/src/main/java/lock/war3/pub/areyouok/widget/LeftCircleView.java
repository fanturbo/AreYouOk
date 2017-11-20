package lock.war3.pub.areyouok.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.Vector;

import lock.war3.pub.areyouok.R;

/**
 * Created by tubro on 2017/11/20.
 */

public class LeftCircleView extends View {
    private Paint mDashPaint;
    private Paint mPaint;
    private int circleRadius = 20;


    public LeftCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint({"InlinedApi", "NewApi"})
    private void init() {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, metrics);
        float dashGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, AutoUtils.getPercentWidthSize(3), metrics);
        float dashWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, AutoUtils.getPercentWidthSize(7), metrics);

        mDashPaint = new Paint();
        mDashPaint.setColor(0xff3b3c4e);
        mDashPaint.setStyle(Paint.Style.STROKE);
        mDashPaint.setStrokeWidth(width);
        mDashPaint.setAntiAlias(true);
        //DashPathEffect是Android提供的虚线样式API，具体的使用可以参考下面的介绍
        mDashPaint.setPathEffect(new DashPathEffect(new float[]{dashWidth, dashGap}, 0));

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mPaint.setStrokeWidth(width);
        mPaint.setColor(getContext().getResources().getColor(R.color.color3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF oval = new RectF(-20, 0,
                circleRadius, circleRadius * 2);
        canvas.drawArc(oval, -90, 180, true, mPaint);
    }
}
