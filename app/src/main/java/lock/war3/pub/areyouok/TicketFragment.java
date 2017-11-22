package lock.war3.pub.areyouok;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lock.war3.pub.areyouok.widget.OffcutView;

public class TicketFragment extends DialogFragment {

    public static final String TAG = TicketFragment.class.getSimpleName();
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
    Unbinder unbinder;
    private Context mContext;

    public static TicketFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt("i", i);
        TicketFragment fragment = new TicketFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_bus_ticket, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        unbinder = ButterKnife.bind(this, rootView);
        setTicket();
        return rootView;
    }

    public void setTicket() {
        int i = getArguments().getInt("i");
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format2 = new SimpleDateFormat("验票时间：MM月dd日HH:mm");
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

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(window.getAttributes().width, window.getAttributes().height);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        this.dismiss();
    }
}
