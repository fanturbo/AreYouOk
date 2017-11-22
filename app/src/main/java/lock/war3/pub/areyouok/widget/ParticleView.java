package lock.war3.pub.areyouok.widget;


import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

@SuppressLint({ "DrawAllocation", "NewApi" })
public class ParticleView extends View {

	private final long ANIM_DURATION = 1200;
	private float mSize;
	private Paint mPaint;
	private float roundRadius = 10;// 小球半径
	private float trackWidth;// 轨迹宽度，这里宽度等于view大小减去小球直径
	private float trackHeight;// 轨迹高度，这里高度等于view大小减去小球直径后的3分之1
	private PointF roundPoint;// 小球的位置点
	private ValueAnimator anim;
	private int round1Color;
	private int round3Color;
	private int round2Color;
	private boolean needTrack = true;

	public ParticleView(Context context) {
		this(context, null);
	}

	public ParticleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 消锯齿
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		
		if (anim == null) {//如果没开启动画，开启动画
			startTrackAnim();
		}
		
		if(round1Color!=0){
			mPaint.setColor(round1Color);
		}
		mPaint.setStrokeWidth(3);
		drawTrack(canvas, roundPoint);//画水平椭圆及小球

		if(round2Color!=0){
			mPaint.setColor(round2Color);
		}
		canvas.rotate(120, mSize / 2, mSize / 2);//旋转一圈是360度，这里有三条退机，所以他们间隔为120度，旋转120度，画椭圆及小球
		drawTrack(canvas, roundPoint);

		
		if(round3Color!=0){
			mPaint.setColor(round3Color);
		}
		canvas.rotate(120, mSize / 2, mSize / 2);//旋转120度，画椭圆及小球
		drawTrack(canvas, roundPoint);

		mPaint.reset();
	}

	@SuppressWarnings("static-access")
	private void startTrackAnim() {
		TypeEvaluator<PointF> evaluator = new OvalTypeEvaluator(trackWidth / 2,
				trackHeight / 2, roundRadius, roundRadius
						+ (mSize - 2 * roundRadius) / 3);//初始化自定义的估值器；
		anim = new ValueAnimator().ofObject(evaluator, new PointF());//初始化动画
		anim.setDuration(ANIM_DURATION);//设置动画周期
		anim.addUpdateListener(new AnimatorUpdateListener() {//监听动画过程
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				//动画变化时，获取到当前小球的位置，重画界面
				roundPoint = (PointF) animation.getAnimatedValue();
				Log.i(ParticleView.class.getSimpleName(),"roundPoint="+roundPoint);
				invalidate();
			}
		});
		anim.setRepeatCount(-1);//设置动画重复次数（-1，为无限重复 ）
		anim.start();//开启动画
	}

	/**
	 * 画轨迹
	 * @param canvas
	 * @param p 小球的位置
	 */
	private void drawTrack(Canvas canvas, PointF p) {
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(2);
		if(needTrack){
		// 画轨迹
		RectF oval = new RectF(roundRadius, roundRadius
				+ (mSize - 2 * roundRadius) / 3, mSize - roundRadius,
				roundRadius + (mSize - 2 * roundRadius) * 2 / 3);
		canvas.drawOval(oval, mPaint);
		}

		// 画小球
		mPaint.setStyle(Style.FILL_AND_STROKE);
		canvas.drawCircle(p.x, p.y, roundRadius, mPaint);
	}

	/**
	 * 设置小球半径
	 * 
	 * @return
	 */
	public void setRoundRadius(float roundRadius) {
		this.roundRadius = roundRadius;
	}
	
	/**
	 * 三条轨迹颜色
	 * @param round1Color
	 * @param round2Color
	 * @param round3Color
	 */
	public void setRoundColor(int round1Color,int round2Color,int round3Color){
		this.round1Color = round1Color;
		this.round2Color = round2Color;
		this.round3Color = round3Color;
	}
	
	/**
	 * 是否是要轨迹
	 */
	public void setNeedTrack(boolean needTrack){
		this.needTrack = needTrack;
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width;
		int height;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			int desired = (int) (getPaddingLeft() + getWidth() + getPaddingRight());
			width = desired;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			int desired = (int) (getPaddingTop() + getWidth() + getPaddingBottom());
			height = desired;
		}
		mSize = width < height ? width : height;// 保证view是正方形
		roundPoint = new PointF(roundRadius, mSize / 2);//小球对应的初始坐标
		trackWidth = mSize - 2 * roundRadius;//轨迹宽，view的宽度-小球直径
		trackHeight = (mSize - 2 * roundRadius) / 3;//轨迹高，（view的宽度-小球直径）/3
		setMeasuredDimension((int) mSize, (int) mSize);
	}
}
