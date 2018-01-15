package wuzhi.fladimir.com.wuzhi.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.adapter.DiaryAdapter;
import wuzhi.fladimir.com.wuzhi.model.database.MySqliteHelper;
import wuzhi.fladimir.com.wuzhi.model.database.SqliteManager;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;
import wuzhi.fladimir.com.wuzhi.util.Jsouper;
import wuzhi.fladimir.com.wuzhi.util.Logger;
import wuzhi.fladimir.com.wuzhi.util.ThreadUtil;

/**
 * Created by Sc_Ji on 2018-01-04.
 * 日记
 */

public class DiaryActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private String userId;
    private String userName;
    private WebView webview;
    private Context mContext;
    private LinearLayout diary_parent;
    private Toolbar diary_tool;
    private Now now = null;

    private boolean following = false;

    private MySqliteHelper mSqlHelper;

    private TextView diary_userName;
    private TextView diary_userFlower;
    private TextView diary_Date;
    private TextView diary_Sign;

    private RecyclerView diary_recycler;
    private DiaryAdapter mDiaryAdapter;
    private ArrayList<Now.diary> mDiaryList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        mContext = DiaryActivity.this;
        userId = getIntent().getStringExtra("id");
        userName = getIntent().getStringExtra("name");
        initView();
        getHtmls();
        //getFavInfo(userId);
    }

    /**
     * @param userId
     */
    private void getFavInfo(String userId) {
        mSqlHelper = SqliteManager.getIntance(mContext);
        following = mSqlHelper.isFlowing(userId);
        Logger.e("ToolBar Menu 数量 = " + diary_tool.getMenu().size());
        /*if (following)
            diary_tool.getMenu().getItem(0).setIcon(R.drawable.ic_tool_favo);
        else diary_tool.getMenu().getItem(0).setIcon(R.drawable.ic_tool_unfavo);*/
    }

    /**
     * Tool 设置
     */
    private void initView() {
        diary_tool = findViewById(R.id.diary_tool);
        diary_userName = findViewById(R.id.diary_userName);
        diary_userFlower = findViewById(R.id.diary_userFlower);
        diary_recycler = findViewById(R.id.diary_recycler);
        diary_Date = findViewById(R.id.diary_Date);
        diary_parent = findViewById(R.id.diary_parent);
        diary_Sign = findViewById(R.id.diary_Sign);

        //tool
        setSupportActionBar(diary_tool);
        diary_tool.setNavigationIcon(R.drawable.ic_tool_back);
        diary_tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        diary_tool.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_color_blue));
        diary_tool.setTitle(userName);
        diary_tool.setTitle("WTF??");
        diary_tool.setTitleTextColor(ContextCompat.getColor(mContext, R.color.white));
        diary_tool.setOnMenuItemClickListener(this);
        //recycler
        diary_recycler.setLayoutManager(new LinearLayoutManager(mContext));
        Now.diary d = new Now.diary();
        d.setDiaryTime("00:01");
        d.setDiaryContent("我如果爱你—— 绝不像攀援的凌霄花, 借你的高枝炫耀自己: 我如果爱你—— 绝不学痴情的鸟儿, 为绿荫重复单调的歌曲");
        mDiaryList.add(d);
        mDiaryAdapter = new DiaryAdapter(mDiaryList);
        diary_recycler.setAdapter(mDiaryAdapter);

        getFavInfo(userId);
    }

    /**
     * 获取Html数据
     */
    private void getHtmls() {
        webview = findViewById(R.id.diary_webview);
        WebSettings settings = webview.getSettings();
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        //settings.setUserAgentString(MyApplication.getUserAgent());
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setAppCachePath(this.getDir("appcache", 0).getPath());
        settings.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        webview.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.java_obj.getSource(document.documentElement.outerHTML);void(0)");
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("https://wuzhi.me/u/" + userId);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (following) {
            item.setIcon(R.drawable.ic_tool_unfavo);
            following = false;
            mSqlHelper.removeFollower(userId);
        } else {
            item.setIcon(R.drawable.ic_tool_favo);
            following = true;
            mSqlHelper.addFollower(userId, userName, now.getUserSign());
        }
        return false;
    }

    /**
     * JavaScript
     */
    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            try {
                now = Jsouper.getCompleteNovel_Android(userId, html);
            } catch (Exception e) {
                Logger.e("Android方式,解析错误--->" + html);
                e.printStackTrace();
                Logger.e("尝试PC分析");
                try {
                    now = Jsouper.getCompleteNovel_Pc(html);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    Logger.e("PC方式,解析错误--->" + html);
                    Logger.e("Web页面登陆后重试");
                    showLoginActivity();
                }
            }

            setUi();
        }
    }

    /**
     * 显示
     */
    private void setUi() {
        ThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (now != null) {
                    diary_userName.setText(now.getUserName());
                    diary_Date.setText(now.getDate());
                    diary_userFlower.setText(now.getFlowers());
                    if (now.getUserSign().length() < 1)
                        diary_Sign.setVisibility(View.GONE);
                    else diary_Sign.setText("--- " + now.getUserSign());

                    // FIXME: 2018-01-15 此处要修改
                    //mDiaryList = now.getDiary();
                    mDiaryList.addAll(now.getDiary());
                    mDiaryAdapter.notifyData(mDiaryList);
                }
            }
        });
    }

    /**
     * 跳转至登陆页面
     */
    private void showLoginActivity() {
        Toast.makeText(mContext, "获取日记详情,请登录!", Toast.LENGTH_SHORT).show();
        Intent loginInt = new Intent(mContext, LoginActivity.class);
        startActivityForResult(loginInt, 1);
    }

    /**
     * 登陆回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            Logger.e("登陆成功并返回!");
            webview.loadUrl("https://wuzhi.me/u/" + userId);
        } else {
            AlertDialog dialog = new AlertDialog.Builder(mContext)
                    .setMessage("需要登陆后才能查看日记内容")
                    .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent loginInt = new Intent(mContext, LoginActivity.class);
                            startActivityForResult(loginInt, 1);
                        }
                    })
                    .setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .create();
            dialog.show();
        }
    }

    /**
     * 绑定Menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diarymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
