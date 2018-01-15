package wuzhi.fladimir.com.wuzhi.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import wuzhi.fladimir.com.wuzhi.R;

/**
 * Created by Sc_Ji on 2018-01-04.
 * 登陆页面
 */

@SuppressLint("SetJavaScriptEnabled")
public class LoginActivity extends AppCompatActivity {
    private WebView login_webview;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
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
        login_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.java_obj.getSource(document.documentElement.outerHTML);void(0)");
                super.onPageFinished(view, url);
            }
        });
        login_webview.setWebChromeClient(new WebChromeClient());
        login_webview.loadUrl("https://wuzhi.me/login");
    }

    public void jump(View view) {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setMessage("是否登陆完成?")
                .setPositiveButton("已登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).create();
        dialog.show();
    }

}
