package cn.bvin.app.proguard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.MyClass;

public class NextActivity extends AppCompatActivity {

    public static void start(Context context, MyClass myclass) {
        Intent starter = new Intent(context, NextActivity.class);
        starter.putExtra("foo",myclass);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        MyClass foo = (MyClass) getIntent().getSerializableExtra("foo");
        Toast.makeText(this, foo.getFoo(), Toast.LENGTH_SHORT).show();
    }
}
