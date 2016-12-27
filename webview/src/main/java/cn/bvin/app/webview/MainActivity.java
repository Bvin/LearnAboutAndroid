package cn.bvin.app.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.bvin.library.common.web.JavascriptBridge;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web);
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
            }
        });
        //JavascriptBridge.with(webView).call("blank");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.load){
            load();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void load(){
        // 如果loadUrl中的url以javascript:开头就会执行js代码，而不会执行到onPageFinish
        // 否则每load一次就会回掉一次onPageFinish
        webView.loadUrl("javascriptblank");

        // 此时的url为data:text/html,javascript:alert(injectedObject.toString())
        // 由此可见loadData不能加载js代码，将当作普通html字符串
        //webView.loadData("javascript:alert(injectedObject.toString())","text/html","utf-8");
    }


}
