package cn.bvin.app.designlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BottomSheetActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, BottomSheetActivity.class);
        context.startActivity(starter);
    }

    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_sheet,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.op){
            if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }else {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
