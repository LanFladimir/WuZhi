package wuzhi.fladimir.com.wuzhi.util;

import android.util.Log;

/**
 * Created by Sc_Ji on 2018-01-03.
 * Logger
 */

public class Logger {
    public static boolean isLog = true;
    private static String TAG = Thread.currentThread().getStackTrace()[3].getMethodName();

    public static void d(String tag, String msg) {
        if (isLog)
            Log.d(tag, msg);
    }

    public static void d(String msg) {
        if (isLog)
            Log.d(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (isLog)
            Log.e(tag, msg);
    }

    public static void e(String msg) {
        if (isLog)
            Log.e(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (isLog)
            Log.w(tag, msg);
    }

    public static void w(String msg) {
        if (isLog)
            Log.w(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (isLog)
            Log.v(tag, msg);
    }

    public static void v(String msg) {
        if (isLog)
            Log.v(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isLog)
            Log.i(tag, msg);
    }

    public static void i(String msg) {
        if (isLog)
            Log.i(TAG, msg);
    }
}