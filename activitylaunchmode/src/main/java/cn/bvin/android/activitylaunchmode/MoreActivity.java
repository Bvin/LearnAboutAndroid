package cn.bvin.android.activitylaunchmode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MoreActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MoreActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MoreActivity", "onDestroy");
    }

    public void launchActivity(View view){
        SingleTaskActivity.start(this);
    }
}
