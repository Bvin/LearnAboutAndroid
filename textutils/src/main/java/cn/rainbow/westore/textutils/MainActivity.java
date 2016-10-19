package cn.rainbow.westore.textutils;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import static android.os.Build.VERSION.SDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text = (TextView) findViewById(R.id.text);

        int avail = text.getLayoutParams().width;

        //6个字符限制(中午三个可带一个英文符号)，单个元素字符宽度不超过avail
        String s = "个字符限制,B, C ,D ,E ,F, G, H, I ,J,K, M, F";
        CharSequence ch = TextUtils.commaEllipsize(s,text.getPaint(),avail,"one more","%d 等等");
        Log.d("onCreate: ",ch.toString());

        ch = TextUtils.ellipsize(s,text.getPaint(),avail, TextUtils.TruncateAt.END);

        text.setText(ch);


        String[] stringArray =  TextUtils.split(s,",");
        System.out.println(TextUtils.join(",",stringArray));

        System.out.println(TextUtils.concat("a","b","v"));
    }
}
