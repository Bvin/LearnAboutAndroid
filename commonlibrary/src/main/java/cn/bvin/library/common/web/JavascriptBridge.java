package cn.bvin.library.common.web;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 *
 * Use extreme care when using this method in a WebView which could contain untrusted content.
 * <p>用于加载信任的内容.
 * JavascriptBridge.on(webview).register(obj,name).call(jscode).remove(name).
 * <p><i>Created by bvin on 2016/12/27.</i>
 */

public class JavascriptBridge {

    private static final String TAG = "JavascriptBridge";

    public static JavascriptBridge on(WebView webView){
        return new JavascriptBridge(webView);
    }

    private WebView mWebView;

    private JavascriptBridge(WebView webView) {
        mWebView = webView;
        // Enable Javascript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        //4)通过以下设置，防止越权访问，跨域等安全问题：
        settings.setAllowFileAccess(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }

        //1)建议开发者通过以下方式移除该JavaScript接口：
        remove("searchBoxJavaBridge_");//CVE-2014-1939
        remove("accessibility");//CVE-2014-7224
        remove("accessibilityTraversal");//CVE-2014-7224
    }

    /**
     * This method is designed to hide how Javascript is injected into
     * the WebView.
     *
     * In KitKat the new evaluateJavascript method has the ability to
     * give you access to any return values via the ValueCallback object.
     *
     * The String passed into onReceiveValue() is a JSON string, so if you
     * execute a javascript method which return a javascript object, you can
     * parse it as valid JSON. If the method returns a primitive value, it
     * will be a valid JSON object, but you should use the setLenient method
     * to true and then you can use peek() to test what kind of object it is,
     *
     * @param javascript
     */
    public void call(String javascript) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // In KitKat+ you should use the evaluateJavascript method
            mWebView.evaluateJavascript(javascript, new ValueCallback<String>() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onReceiveValue(String s) {
                    Log.d(TAG, "onReceiveValue: "+s);
                }
            });
        } else {
            /**
             * For pre-KitKat+ you should use loadUrl("javascript:<JS Code Here>");
             * To then call back to Java you would need to use addJavascriptInterface()
             * and have your JS call the interface
             **/
            mWebView.loadUrl("javascript:"+javascript);
        }
    }

    public void register(Object object, String name){
        mWebView.addJavascriptInterface(object, name);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void remove(String name) {
        mWebView.removeJavascriptInterface(name);
    }
}
