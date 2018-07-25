package lock.war3.pub.areyouok;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lock.war3.pub.areyouok.widget.OffcutView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HaluoWeChatActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.frame_alpha)
    FrameLayout frameAlpha;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.shuttle_ticket_date)
    TextView shuttleTicketDate;
    @BindView(R.id.suhttle_ticket_time)
    TextView suhttleTicketTime;
    @BindView(R.id.shuttle_ticket_offcutview)
    OffcutView shuttleTicketOffcutview;
    @BindView(R.id.ticket_container_top_view)
    FrameLayout ticketContainerTopView;
    @BindView(R.id.shuttle_ticket_title)
    TextView shuttleTicketTitle;
    @BindView(R.id.shuttle_ticket_underline)
    View shuttleTicketUnderline;
    @BindView(R.id.shuttle_ticket_price)
    TextView shuttleTicketPrice;
    @BindView(R.id.ticket_container_middle_view)
    LinearLayout ticketContainerMiddleView;
    @BindView(R.id.shuttle_desc_text)
    TextView shuttleDescText;
    @BindView(R.id.shuttle_ticket_check)
    TextView shuttleTicketCheck;
    @BindView(R.id.ticket_container_bottom_view)
    LinearLayout ticketContainerBottomView;
    @BindView(R.id.linear_ticket)
    LinearLayout linearTicket;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.login_ll_hide)
    LinearLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haluo_wechat);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if ("Xiaomi".equals(Build.MANUFACTURER)) {
                StatusBarUtil.setColor(this, getResources().getColor(android.R.color.white), 0);
                Utils.StatusBarLightMode(this, 1);
            } else if ("MEIZU".equals(Build.MANUFACTURER)) {
                //识别魅族手机 测试下魅族5.0的手机
                StatusBarUtil.setColor(this, getResources().getColor(android.R.color.white), 0);
                Utils.StatusBarLightMode(this, 2);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StatusBarUtil.setColor(this, getResources().getColor(android.R.color.white), 0);
                Utils.StatusBarLightMode(this, 3);
            } else {
                StatusBarUtil.setColor(this, getResources().getColor(android.R.color.white), 0);
            }
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(android.R.color.white), 0);
        }
        ButterKnife.bind(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTicket(0);
                linearTicket.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTicket(1);
                linearTicket.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTicket(2);
                linearTicket.setVisibility(View.VISIBLE);
            }
        });
        //判断是否有底部导航栏
        if (getWindowManager().getDefaultDisplay().getHeight() < AutoLayoutConifg.getInstance().getScreenHeight()) {
            TextView textView = new TextView(this);
            textView.setHeight(AutoUtils.getPercentHeightSize(40));
            linearTicket.addView(textView, 0);
        } else {
            TextView textView = new TextView(this);
            textView.setHeight(AutoUtils.getPercentHeightSize(100));
            linearTicket.addView(textView, 0);
        }
    }

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        linearTicket.setVisibility(View.GONE);
    }

    public void setTicket(int i) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format2 = new SimpleDateFormat("验票时间：HH:mm:ss");
        shuttleTicketDate.setText(format.format(Calendar.getInstance().getTime()));
        int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (i == 0) {
            suhttleTicketTime.setText("06:30-09:30");
            shuttleTicketTitle.setText("K003");
            shuttleDescText.setText("一票一人仅限乘坐K003");
        } else if (i == 1) {
            suhttleTicketTime.setText("17:30-21:00");
            shuttleTicketTitle.setText("K004");
            shuttleDescText.setText("一票一人仅限乘坐K004");
        } else {
            suhttleTicketTime.setText("06:30-09:30");
            shuttleTicketTitle.setText("K005");
            shuttleDescText.setText("一票一人仅限乘坐K005");
        }
        shuttleTicketCheck.setText(format2.format(Calendar.getInstance().getTime()));
        //判断是否是周五来设置价格

        int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 6) {
            shuttleTicketPrice.setText("票价1元");
        } else {
            shuttleTicketPrice.setText("票价2元");
        }
    }
}
