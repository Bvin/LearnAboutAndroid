package cn.bvin.app.proguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Foo mFoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = "{\"ret\":\"THOPT_70003\",\"msg\":\"\\u9001\\u8fbe\\u533a\\u57dfID\\u4e0d\\u80fd\\u4e3a\\u7a7a\\uff01\",\"fare\":null,\"disPlayName\":null,\"deliverTypeCode\":\"52\",\"deliverTypeTitle\":\"\\u5546\\u5bb6\\u81ea\\u914d\\u9001\"}";
        final Foo foo = new Gson().fromJson(json, Foo.class);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, foo.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
