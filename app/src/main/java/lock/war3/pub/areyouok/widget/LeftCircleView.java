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
    private Paint mPaint;
    private int circleRadius = 22;


    public LeftCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint({"InlinedApi", "NewApi"})
    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mPaint.setColor(getContext().getResources().getColor(R.color.text3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF oval = new RectF(0-circleRadius, 0,
                circleRadius, circleRadius * 2);
        Path path = new Path();
        path.addArc(oval, -90, 180);
        path.addRect(0, 0, getWidth(), getHeight(), Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, mPaint);
    }
}
