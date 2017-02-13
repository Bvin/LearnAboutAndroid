package cn.bvin.android.activitylaunchmode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingleTaskActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchActivity(View view){
        OtherActivity.start(this);
    }
}
