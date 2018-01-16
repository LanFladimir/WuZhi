package wuzhi.fladimir.com.wuzhi.util;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sc_Ji on 2018-01-16.
 */

public class ShareHelper {

    private static final String FACT = "wuzhi";
    public static final String FOLLOW = "follow";//关注列表变化

    public static void setBoolean(Context mContext, String key, boolean value) {
        SharedPreferences shared = mContext.getSharedPreferences(FACT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context mContext, String key) {
        SharedPreferences shared = mContext.getSharedPreferences(FACT, Context.MODE_PRIVATE);
        return shared.getBoolean(key, false);
    }
}
