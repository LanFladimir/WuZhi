package wuzhi.fladimir.com.wuzhi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import wuzhi.fladimir.com.wuzhi.model.entity.Now;
import wuzhi.fladimir.com.wuzhi.util.Jsouper;
import wuzhi.fladimir.com.wuzhi.util.Logger;

/**
 * Created by Sc_Ji on 2018-01-04.
 */

public class NovelActivity extends AppCompatActivity {
    String id;
    WebView webview;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);
        mContext = NovelActivity.this;
        id = getIntent().getStringExtra("id");
        Logger.e("UserId-->" + id);
        getHtmls();
    }


    /**
     * 获取Html数据
     */
    private void getHtmls() {
        webview = findViewById(R.id.novel_webview);
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
        webview.loadUrl("https://wuzhi.me/u/" + id);
    }

    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            Document novel = Jsoup.parse(html);
            Now now = null;
            try {
                now = Jsouper.getCompleteNovel_Pc(id, html);
            } catch (Exception e) {
                Logger.e("PC方式,解析错误--->" + html);
                e.printStackTrace();
                Logger.e("尝试Android分析");
                try {
                    now = Jsouper.getCompleteNovel_Android(id, html);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    Logger.e("Android方式,解析错误--->" + html);
                    Logger.e("Web页面登陆后重试");
                    showLoginActivity();
                }
            }
            setUi(now);
        }
    }

    /**
     * 跳转至登陆页面
     */
    private void showLoginActivity() {
        Toast.makeText(mContext, "获取日记详情,请登录!", Toast.LENGTH_SHORT).show();
        Intent loginInt = new Intent(mContext, LoginActivity.class);
        startActivityForResult(loginInt, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            Logger.e("登陆成功并返回!");
            webview.reload();
        }
    }

    private void setUi(Now now) {
        Logger.e("Id-->" + now.getUserId());
        Logger.e("Img-->" + now.getUserImg());
        Logger.e("Name-->" + now.getUserName());
        Logger.e("Sign-->" + now.getUserSign());
        Logger.e("Date-->" + now.getDate());
        Logger.e("Flowers-->" + now.getFlowers());
        for (Now.novels novels : now.getNovels()) {
            Logger.e("novels-->time = " + novels.getNovelTime() + "\ncontent = " + novels.getNovelContent());
        }
    }
}
