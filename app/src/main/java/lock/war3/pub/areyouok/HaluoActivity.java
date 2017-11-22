package lock.war3.pub.areyouok;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HaluoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haluo);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketFragment ticketFragment = TicketFragment.newInstance(0);
                ticketFragment.show(getSupportFragmentManager(), TicketFragment.TAG);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketFragment ticketFragment = TicketFragment.newInstance(1);
                ticketFragment.show(getSupportFragmentManager(), TicketFragment.TAG);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketFragment ticketFragment = TicketFragment.newInstance(2);
                ticketFragment.show(getSupportFragmentManager(), TicketFragment.TAG);
            }
        });
    }
}
