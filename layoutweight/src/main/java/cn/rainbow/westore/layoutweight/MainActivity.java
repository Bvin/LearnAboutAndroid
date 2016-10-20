package cn.rainbow.westore.layoutweight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.editText);
        TextView behind = (TextView) findViewById(R.id.button);
        textView.setText("￥1199-￥1299");
        behind.setText("￥1399");
    }
}
