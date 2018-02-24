package wuzhi.fladimir.com.wuzhi.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.model.entity.Follow;

/**
 * Created by Sc_Ji on 2018-01-03.
 * Db
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase mSqlDataBase;

    public MySqliteHelper(Context context) {
        super(context, DbConstant.DATABASE_NAME, null, DbConstant.DATABASE_VERSION);
        mSqlDataBase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstant.CREAT_TABLE_FOLLOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 查询
     *
     * @param table
     * @param args
     * @return
     */
    private static Cursor query(String table, String[] args) {
        return mSqlDataBase.query(table, args,
                null, null, null, null, null);
    }


    /**
     * 清空数据表
     *
     * @param tableName
     */
    public void ClearTable(String tableName) {
        mSqlDataBase.execSQL("DELETE FROM" + tableName);
    }

    /**
     * 读取关注列表
     *
     * @return
     */
    public ArrayList<Follow> getFollowList() {
        ArrayList<Follow> mList = new ArrayList<>();
        Cursor cursor = query(DbConstant.TABLE_NAME_FOLLOW, new String[]{DbConstant.FOLLOW_ID,
                DbConstant.FOLLOW_NAME, DbConstant.FOLLOW_SIGN,DbConstant.FOLLOW_IMG});
        while (cursor.moveToNext()) {
            Follow follow = new Follow();
            follow.setUserId(cursor.getString(cursor.getColumnIndex(DbConstant.FOLLOW_ID)));
            follow.setUserName(cursor.getString(cursor.getColumnIndex(DbConstant.FOLLOW_NAME)));
            follow.setUserSign(cursor.getString(cursor.getColumnIndex(DbConstant.FOLLOW_SIGN)));
            follow.setUserImg(cursor.getString(cursor.getColumnIndex(DbConstant.FOLLOW_IMG)));
            mList.add(follow);
        }
        cursor.close();
        //mSqlDataBase.close();
        return mList;
    }

    /**
     * 是否正在关注
     *
     * @param userId 用户id
     */
    public boolean isFlowing(String userId) {
        Cursor cursor = mSqlDataBase.query(DbConstant.TABLE_NAME_FOLLOW,
                new String[]{DbConstant.FOLLOW_ID},
                "id = ? ", new String[]{userId},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    /**
     * 新增关注
     *
     * @param id
     * @param userName
     */
    public void addFollower(String id, String userName, String userSign, String userImg) {
        ContentValues values = new ContentValues();
        values.put(DbConstant.FOLLOW_ID, id);
        values.put(DbConstant.FOLLOW_NAME, userName);
        values.put(DbConstant.FOLLOW_SIGN, userSign);
        values.put(DbConstant.FOLLOW_IMG, userImg);
        mSqlDataBase.insert(DbConstant.TABLE_NAME_FOLLOW, null, values);
    }

    /**
     * 取关
     *
     * @param userId
     */
    public void removeFollower(String userId) {
        mSqlDataBase.delete(
                DbConstant.TABLE_NAME_FOLLOW,
                DbConstant.FOLLOW_ID + " = ?",
                new String[]{userId});
    }
}
