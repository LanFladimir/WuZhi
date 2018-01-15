package wuzhi.fladimir.com.wuzhi.model.database;

/**
 * Created by Sc_Ji on 2018-01-03.
 */

public class DbConstant {
    public static final String DATABASE_NAME = "wuzhi.db";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME_Follow = "follow";
    public static final String FOLLOW_ID = "id";
    public static final String FOLLOW_NAME = "name";
    public static final String FOLLOW_SIGN = "sign";
    public static final String CREAT_TABLE_FOLLOW =
            "create table if not exists " + DbConstant.TABLE_NAME_Follow + "(" +
                    DbConstant.FOLLOW_ID + " varchar(20) primary key ," +
                    DbConstant.FOLLOW_SIGN + " varchar(20) ," +
                    DbConstant.FOLLOW_NAME + " varchar(20))";
}