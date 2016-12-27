package cn.bvin.app.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.web);
        String html = "<div style=\"max-width:480px;margin:0 auto;\">" +
                "<style type=\"text/css\">p {max-width:480px;} img {width: 100%;height:auto;} input {width: 100%;height:auto;}" +
                "</style>" +
                "测试</div>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("about:blank",html,"text/html","utf-8","");
        webView.setWebChromeClient(new WebChromeClient()/*{
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.d(TAG, "onJsAlert: "+message);
                return super.onJsAlert(view, url, message, result);
            }
        }*/);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished: "+url);
                view.loadUrl("javascript:alert('onPageFinished')");
            }
        });
    }
}
