package cn.rainbow.westore.textutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import cn.bvin.lib.base.text.Ellipsize;

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

        float rw = Layout.getDesiredWidth(s,text.getPaint());
        Log.d("行宽-"+rw,"view宽-"+avail);

        String afterTruncate = "等等";
        float afterTruncateDesiredWidth = Layout.getDesiredWidth(afterTruncate,text.getPaint());


        ch = TextUtils.ellipsize(s,text.getPaint(),avail-afterTruncateDesiredWidth, TextUtils.TruncateAt.END);

        text.setText(Ellipsize.concat(s,afterTruncate,avail,text.getPaint(), TextUtils.TruncateAt.END));


        String[] stringArray =  TextUtils.split(s,",");
        System.out.println(TextUtils.join(",",stringArray));

        System.out.println(TextUtils.concat("a","b","v"));
    }
}
