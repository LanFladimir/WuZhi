package wuzhi.fladimir.com.wuzhi.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;
import wuzhi.fladimir.com.wuzhi.util.Jsouper;
import wuzhi.fladimir.com.wuzhi.util.Logger;

public class SplashActivity extends AppCompatActivity {
    WebView mHiddenWebView;
    private String userId = "133658";//我的id
    private Now now = null;
    private Context mContext;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;
        mHiddenWebView = findViewById(R.id.hidden_webview);
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Check Login State!");
        mDialog.show();


        checkLoginState();
    }

    /**
     * if login?
     */
    private void checkLoginState() {
        WebSettings settings = mHiddenWebView.getSettings();
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
        mHiddenWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        mHiddenWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.java_obj.getSource(document.documentElement.outerHTML);void(0)");
                super.onPageFinished(view, url);
            }
        });
        mHiddenWebView.setWebChromeClient(new WebChromeClient());
        mHiddenWebView.loadUrl("https://wuzhi.me/u/" + userId);
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
                    mDialog.dismiss();
                    Toast.makeText(mContext, "Not Login Yet,请登录!", Toast.LENGTH_SHORT).show();
                    Intent loginInt = new Intent(mContext, LoginActivity.class);
                    startActivityForResult(loginInt, 1);
                }
            }
            mDialog.dismiss();
            Toast.makeText(mContext, "Alrady Login!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(mContext, MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            startActivity(new Intent(mContext, MainActivity.class));
        }
    }
}
