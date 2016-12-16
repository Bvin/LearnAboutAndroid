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
            // 只要是ViewGroup里的任意子View调了这句就会引发layout和draw
            // 将会调用父类的invalidate()和requestLayout方法
            // 只有悬浮式刷新需要调用bringToFront，其他可以直接调用requestLayout()
            mButton.offsetTopAndBottom(15);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
