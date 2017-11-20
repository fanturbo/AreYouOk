package lock.war3.pub.areyouok;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by tubro on 2017/11/20.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
