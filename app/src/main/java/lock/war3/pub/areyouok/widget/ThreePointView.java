package lock.war3.pub.areyouok.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tubro on 2018/3/12.
 */

public class ThreePointView extends View {
    public ThreePointView(Context context) {
        super(context);
    }

    public ThreePointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreePointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ThreePointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 5, paint);
        canvas.drawCircle(getWidth() / 2 - 22, getHeight() / 2, 5, paint);
        canvas.drawCircle(getWidth() / 2 + 22, getHeight() / 2, 5, paint);
    }
}
