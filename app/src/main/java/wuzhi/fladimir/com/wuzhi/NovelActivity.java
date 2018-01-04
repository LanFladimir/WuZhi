package wuzhi.fladimir.com.wuzhi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
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
                now = Jsouper.getCompleteNovel(id, html);
            } catch (Exception e) {
                Logger.e("解析错误--->" + html);
                e.printStackTrace();
            }
            setUi(now);
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
