package cn.bvin.app.proguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.MyClass;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Foo mFoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = "{\"ret\":\"THOPT_70003\",\"msg\":\"\\u9001\\u8fbe\\u533a\\u57dfID\\u4e0d\\u80fd\\u4e3a\\u7a7a\\uff01\",\"fare\":null,\"disPlayName\":null,\"deliverTypeCode\":\"52\",\"deliverTypeTitle\":\"\\u5546\\u5bb6\\u81ea\\u914d\\u9001\"}";
        final Foo foo = new Gson().fromJson(json, Foo.class);
        mFoo = new Foo();
        mFoo.setMsg("没有被混淆？");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, mFoo.getMsg(), Toast.LENGTH_SHORT).show();
                /*MyClass myClass = new MyClass();
                myClass.setFoo("Foo!");
                myClass.setI(45);
                NextActivity.start(MainActivity.this, myClass);*/
                Barz barz = new Barz();
                barz.setBarz("af");
            }
        });
    }

    //-keep public class * extends cn.bvin.app.proguard.Bar
    //private/default 会全部混淆
    //protected 除类名一样(包括构造)全部混淆
    //protected static/public 除类名一样全部混淆
    public class Barz extends Bar{
        private String barz;

        public String getBarz() {
            return barz;
        }

        public void setBarz(String barz) {
            this.barz = barz;
        }
    }
}
