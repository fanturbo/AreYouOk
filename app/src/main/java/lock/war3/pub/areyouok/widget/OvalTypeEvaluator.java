package lock.war3.pub.areyouok.widget;


import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class OvalTypeEvaluator implements TypeEvaluator<PointF> {
	
	private float a;//椭圆短半轴
	private float b;//椭圆长半轴
	private float pandingLeft;//椭圆轨迹离view的左边距
	private float pandingTop;//椭圆轨迹离view的上边距


	/**
	 * 
	 * @param a 椭圆长半轴
	 * @param b 椭圆短半轴
	 * @param pandingLeft 椭圆轨迹离view的左边距
	 * @param pandingTop 椭圆轨迹离view的上边距
	 */
	public OvalTypeEvaluator(float a,float b,float pandingLeft,float pandingTop) {
		this.a = a;
		this.b = b;
		this.pandingLeft = pandingLeft;
		this.pandingTop = pandingTop;
	}
	

	@Override
	public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
		//椭圆标准公式x^2/a^2+y^2/b^2=1,其中a为椭圆长轴，b为椭圆短轴；
		PointF p = new PointF();
		if(fraction<=0.5){
			//动画前半段，x坐标是递增的
			p.x = pandingLeft+a*2.0f*fraction*2.0f;
			p.y = b+pandingTop-(float)(Math.sqrt(1-(p.x-pandingLeft-a)*(p.x-pandingLeft-a)/a/a))*b;
		}else{
			//动画后半段,x是递减的
			p.x = pandingLeft+a*2-(2*a*(fraction-0.5f)*2);
			p.y = b+pandingTop+(float)(Math.sqrt(1-(p.x-pandingLeft-a)*(p.x-pandingLeft-a)/a/a))*b;
		}
		return p;
	}

}
