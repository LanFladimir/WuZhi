package wuzhi.fladimir.com.wuzhi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wuzhi.fladimir.com.wuzhi.util.Jsouper;
import wuzhi.fladimir.com.wuzhi.util.Logger;

/**
 * Created by Sc_Ji on 2018-01-04.
 * 登陆页面
 */

@SuppressLint("SetJavaScriptEnabled")
public class LoginActivity extends AppCompatActivity {
    private WebView login_webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_webview = findViewById(R.id.login_webview);

        WebSettings settings = login_webview.getSettings();

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
        login_webview.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        login_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(LoginActivity.this, "onPageFinished", Toast.LENGTH_SHORT).show();
                view.loadUrl("javascript:window.java_obj.getSource(document.documentElement.outerHTML);void(0)");
                super.onPageFinished(view, url);
            }
        });
        login_webview.setWebChromeClient(new WebChromeClient());
        login_webview.loadUrl("https://wuzhi.me/");
    }

    public void jump(View view) {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Jsouper.getCompleteNovel(255148);
            }
        }).start();*/
        StringRequest request = new StringRequest(login_webview.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.e(response);
                try {
                    Document doc = Jsoup.parse(response);
                    Logger.e("当前Url", login_webview.getUrl());
                    Logger.e("title", doc.title());
                    /*Logger.e("签名", doc.getElementsByClass("quote").get(0)
                            .getElementsByTag("span").text());
                    Logger.e("title", doc.title());
                    Logger.e("userName", doc.getElementsByClass("note_username").get(0)
                            .text());*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, null);
        Volley.newRequestQueue(LoginActivity.this).add(request);
        //startActivity(new Intent(LoginActivity.this, TestActivity.class));
    }

    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            Logger.e("当前页面内容", html);
        }
    }
}
