package wuzhi.fladimir.com.wuzhi.model.database;

import android.content.Context;

/**
 * Created by Sc_Ji on 2018-01-03.
 */

public class SqliteManager {
    private static MySqliteHelper mMySqliteHelper;

    public static MySqliteHelper getIntance(Context context) {
        if (mMySqliteHelper == null) {
            mMySqliteHelper = new MySqliteHelper(context);
        }
        return mMySqliteHelper;
    }
}
