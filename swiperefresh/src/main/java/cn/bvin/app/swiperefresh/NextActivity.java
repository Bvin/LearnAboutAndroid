package cn.bvin.app.swiperefresh;

import android.content.Context;
import android.content.Intent;
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
        setContentView(R.layout.activity_next);
        osr = (OldSwipeRefreshLayout) findViewById(R.id.activity_next);
        osr.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        osr.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh){
            osr.setRefreshing(true);
            onRefresh();
            return true;
        }else if (item.getItemId() == R.id.next){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
