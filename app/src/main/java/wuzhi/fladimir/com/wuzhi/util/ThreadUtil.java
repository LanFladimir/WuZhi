package wuzhi.fladimir.com.wuzhi.util;

import wuzhi.fladimir.com.wuzhi.App;

/**
 * Created by Sc_Ji on 2018-01-15.
 */

public class ThreadUtil {

    public static void runOnUiThread(Runnable runnable) {
        if (android.os.Process.myTid() == App.getMainId()) {
            //Logger.e("在主线程中直接运行即可");
            runnable.run();
        } else {
            //Logger.e("说明在子线程中,通过handler的post来执行");
            App.getHandler().post(runnable);
        }
    }
}
