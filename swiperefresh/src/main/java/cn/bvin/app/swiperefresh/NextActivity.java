package cn.bvin.app.swiperefresh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NextActivity extends AppCompatActivity implements OldSwipeRefreshLayout.OnRefreshListener {

    private OldSwipeRefreshLayout osr;

    public static void start(Context context) {
        Intent starter = new Intent(context, NextActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_next);
        osr = (OldSwipeRefreshLayout) findViewById(R.id.activity_next);
        osr.setOnRefreshListener(this);
        osr.setColorSchemeRes(R.color.colorAccent,R.color.colorPrimary,android.R.color.holo_green_dark,android.R.color.holo_orange_dark);
    }

    @Override
    public void onRefresh() {
        execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh){
            osr.performRefresh();
            return true;
        }else if (item.getItemId() == R.id.next){
            GestureRefreshActivity.start(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void execute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        osr.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
