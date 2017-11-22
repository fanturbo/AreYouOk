package lock.war3.pub.areyouok;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import lock.war3.pub.areyouok.widget.ParticleView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CaiHongTicketActivity extends AutoLayoutActivity {

    @BindView(R.id.ib_back)
    ImageView ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.shuttle_tv_line)
    TextView shuttleTvLine;
    @BindView(R.id.shuttle_tv_start)
    TextView shuttleTvStart;
    @BindView(R.id.shuttle_tv_end)
    TextView shuttleTvEnd;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.shuttle_tv_date)
    TextView shuttleTvDate;
    @BindView(R.id.bus_num)
    TextView busNum;
    @BindView(R.id.shuttle_tv_bus_num)
    TextView shuttleTvBusNum;
    @BindView(R.id.real_pay)
    TextView realPay;
    @BindView(R.id.shuttle_tv_real_pay)
    TextView shuttleTvRealPay;
    @BindView(R.id.shuttle_tv_bus_state)
    TextView shuttleTvBusState;
    @BindView(R.id.shuttle_bus_state)
    TextView shuttleBusState;
    @BindView(R.id.shuttle_tv_bus_time)
    TextView shuttleTvBusTime;
    @BindView(R.id.shuttle_bus_time)
    TextView shuttleBusTime;
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.shuttle_ll_order_number)
    TextView shuttleLlOrderNumber;
    @BindView(R.id.car)
    TextView car;
    @BindView(R.id.car_num)
    TextView carNum;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.shuttle_btn_pay)
    Button shuttleBtnPay;
    @BindView(R.id.scrolll)
    ScrollView scrolll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_hong_ticket);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color4));
        initData();
    }

    private void initData() {
        int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (hourOfDay < 12) {
            shuttleTvLine.setText("H001紫金新干线~回龙观东大街");
            shuttleTvStart.setText("紫金新干线二区西门");
            shuttleTvEnd.setText("回龙观东大街地铁站");
            shuttleTvDate.setText(format.format(Calendar.getInstance().getTime()));
            shuttleTvBusNum.setText("H001紫金新干线~回龙观东大街");
            shuttleTvRealPay.setText("2.00");
            shuttleTvBusState.setText("已支付");
            shuttleTvBusTime.setText(format2.format(Calendar.getInstance().getTime()));
        } else {
            shuttleTvLine.setText("H001回龙观东大街~紫金新干线");
            shuttleTvStart.setText("回龙观东大街D口");
            shuttleTvEnd.setText("紫金新干线2区");
            shuttleTvDate.setText(format.format(Calendar.getInstance().getTime()));
            shuttleTvBusNum.setText("H001回龙观东大街~紫金新干线");
            shuttleTvRealPay.setText("2.00");
            shuttleTvBusState.setText("已支付");
            shuttleTvBusTime.setText(format2.format(Calendar.getInstance().getTime()));
        }
        tvDate.setText(format.format(Calendar.getInstance().getTime()));
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(tvDate, "textColor", 0XFFAA5652, 0XFF97549B, 0XFF6661AE, 0XFF676F22, 0XFFAE5365, 0XFF956033, 0XFFA65948)
                .setDuration(7000);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();

    }

}
