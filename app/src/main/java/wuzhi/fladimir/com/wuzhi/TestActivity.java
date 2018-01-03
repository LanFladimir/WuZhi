package wuzhi.fladimir.com.wuzhi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import wuzhi.fladimir.com.wuzhi.model.database.DbConstant;
import wuzhi.fladimir.com.wuzhi.model.database.MySqliteHelper;
import wuzhi.fladimir.com.wuzhi.model.database.SqliteManager;

/**
 * Created by Sc_Ji on 2018-01-03.
 * 测试
 */

public class TestActivity extends AppCompatActivity {
    EditText idEt;
    EditText nameEt;
    EditText signEt;
    MySqliteHelper mySqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        idEt = findViewById(R.id.id);
        signEt = findViewById(R.id.sign);
        nameEt = findViewById(R.id.name);
        mySqliteHelper = SqliteManager.getIntance(TestActivity.this);
    }

    public void insert(View view) {
        int id = Integer.valueOf(idEt.getText().toString());
        String name = nameEt.getText().toString();
        String sign = signEt.getText().toString();

        mySqliteHelper.InsertFollower(id, name, sign);
    }

    public void Clear(View v) {
        mySqliteHelper.ClearTable(DbConstant.TABLE_NAME_Follow);
    }
}
