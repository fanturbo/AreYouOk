package lock.war3.pub.areyouok;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

public class OffcutView extends View {
    private String a = "";
    private Paint b;
    private Paint c;
    private int d = -1;
    private int f = 12;
    private Fang g = Fang.RIGHT;

    public enum Fang {
        LEFT,
        RIGHT
    }

    public OffcutView(Context context) {
        super(context);
        a();
    }

    public OffcutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public OffcutView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setBgColor(int i) {
        invalidate();
    }

    private void a() {
        this.b = new Paint();
        this.c = new Paint();
        this.b.setAntiAlias(true);
        this.c.setAntiAlias(true);
        this.c.setStyle(Style.FILL);
    }

    public void setText(String str) {
        if (str == null) {
            str = "";
        }
        this.a = str;
        invalidate();
    }

    public void setOffcutType(Fang aVar) {
        this.g = aVar;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        if (this.g == Fang.LEFT) {
            a(canvas);
        } else {
            b(canvas);
        }
    }

    private void a(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        this.c.setColor(getContext().getResources().getColor(R.color.color2));
        canvas.drawCircle((float) this.f, (float) this.f, (float) this.f, this.c);
        Path path = new Path();
        path.moveTo((float) this.f, 0.0f);
        path.lineTo((float) width, 0.0f);
        path.lineTo(0.0f, (float) height);
        path.lineTo(0.0f, (float) this.f);
        path.close();
        canvas.drawPath(path, this.c);
        canvas.save();
        this.b.setColor(this.d);
        this.b.setAntiAlias(true);
        this.b.setTextSize((float) (width / 4));
        float f = ((((float) width) * 0.707f) * 8.0f) / 10.0f;
        float f2 = (-this.b.measureText(this.a)) / 2.0f;
        canvas.rotate(-45.0f);
        canvas.drawText("已验票", f2, f, this.b);
        canvas.restore();
    }

    private void b(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        this.c.setColor(getContext().getResources().getColor(R.color.color2));
        canvas.drawCircle((float) (width - this.f), (float) this.f, (float) this.f, this.c);
        Path path = new Path();
        path.moveTo((float) (width - this.f), 0.0f);
        path.lineTo(0.0f, 0.0f);
        path.lineTo((float) width, (float) height);
        path.lineTo((float) width, (float) this.f);
        path.close();
        canvas.drawPath(path, this.c);
        canvas.save();
        this.b.setColor(this.d);
        this.b.setAntiAlias(true);
        this.b.setTextSize(AutoUtils.getPercentWidthSize(30));
        float measureText = this.b.measureText(this.a);
        canvas.rotate(45.0f, (float) (width / 2), (float) (height / 2));
        canvas.drawText("已验票", AutoUtils.getPercentWidthSize(42), AutoUtils.getPercentWidthSize(42), this.b);
        canvas.restore();
    }
}