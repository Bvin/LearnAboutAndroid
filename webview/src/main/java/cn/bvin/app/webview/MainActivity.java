package cn.bvin.app.webview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
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

        mJavascriptBridge = JavascriptBridge.on(webView);
        mJavascriptBridge.register(new JsObj(),"jsobj");
        mJavascriptBridge.register(new NObject(),"nobj");
    }

    class JsObj{

        @JavascriptInterface
        public String toString(String message) {
            return message;
        }

    }

    class NObject{

        @JavascriptInterface
        public void showNotification(String message) {
            Log.d(TAG, "showNotification: "+message);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(MainActivity.this)
                            .setTicker(message)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Js消息")
                            .setContentText(message)
                            .setAutoCancel(true);

            // Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
            resultIntent.putExtra("EXTRA_FROM_NOTIFICATION", true);

            // The stack builder object will contain an artificial back stack for the
            // started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(MainActivity.class);
            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            // mId allows you to update the notification later on.
            mNotificationManager.notify(-1, mBuilder.build());
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
            mJavascriptBridge.call("nobj.showNotification('"+item.getTitle().toString()+"')");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
