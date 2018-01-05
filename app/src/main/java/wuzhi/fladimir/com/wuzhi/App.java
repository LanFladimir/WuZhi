package wuzhi.fladimir.com.wuzhi;

import android.app.Application;

import wuzhi.fladimir.com.wuzhi.util.Logger;

/**
 * Created by Sc_Ji on 2018-01-03.
 * Application
 */

public class App extends Application {
    @Override
    public void onCreate() {
        Logger.isLog = true;
        super.onCreate();
    }
}
