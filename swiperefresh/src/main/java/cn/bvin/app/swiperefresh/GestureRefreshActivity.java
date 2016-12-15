package cn.bvin.app.swiperefresh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class GestureRefreshActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, GestureRefreshActivity.class);
        context.startActivity(starter);
    }

    private GestureRefreshLayout mGestureRefreshLayout;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_refresh);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mGestureRefreshLayout = (GestureRefreshLayout) findViewById(R.id.grl);
        mButton = (Button) mGestureRefreshLayout.findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.invalidate){
            mButton.bringToFront();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
