package wuzhi.fladimir.com.wuzhi;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import java.security.MessageDigest;

import wuzhi.fladimir.com.wuzhi.util.Logger;

/**
 * Created by Sc_Ji on 2018-01-03.
 * Application
 */

public class App extends Application {
    private static int mainId;
    private static Handler mainHandler;

    @Override
    public void onCreate() {
        Logger.isLog = true;
        super.onCreate();

        mainId = android.os.Process.myTid();
        mainHandler = new Handler();
    }

    public static int getMainId() {
        return mainId;
    }

    public static Handler getHandler() {
        return mainHandler;
    }
}
