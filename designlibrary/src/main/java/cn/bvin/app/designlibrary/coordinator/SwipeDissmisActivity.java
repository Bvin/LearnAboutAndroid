package cn.bvin.app.designlibrary.coordinator;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.bvin.app.designlibrary.R;

public class SwipeDissmisActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SwipeDissmisActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dissmis);

        SwipeDismissBehavior<View> sdb = new SwipeDismissBehavior<>();
        sdb.setDragDismissDistance(300);
        sdb.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) findViewById(R.id.textView).getLayoutParams();
        lp.setBehavior(sdb);

    }
}
