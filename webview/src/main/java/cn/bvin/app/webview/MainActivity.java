package cn.bvin.app.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.bvin.library.common.web.JavascriptBridge;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private WebView webView;
    private JavascriptBridge mJavascriptBridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web);
        String html = "<div style=\"max-width:480px;margin:0 auto;\">" +
                "<style type=\"text/css\">p {max-width:480px;} img {width: 100%;height:auto;} input {width: 100%;height:auto;}" +
                "</style>" +
                "测试</div>";
        webView.loadDataWithBaseURL("about:blank",html,"text/html","utf-8","");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished: "+url);
            }
        });

        mJavascriptBridge = JavascriptBridge.with(webView);
        mJavascriptBridge.register(new JsObj(),"jsobj");
    }

    class JsObj{

        @JavascriptInterface
        public String toString() {
            return "JsObj{}";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.load){
            mJavascriptBridge.call("alert(jsobj.toString())");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
