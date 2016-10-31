package cn.bvin.app.bottomsheet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bvin.lib.base.adapter.SimpleRecyclerAdapter;
import cn.bvin.lib.base.res.ResourcesFinder;
import cn.bvin.lib.base.view.BaseViewHolder;

public class ContextActivity extends FragmentActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ContextActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SimpleRecyclerAdapter<String,BaseViewHolder>(data) {

            @Override
            public BaseViewHolder createViewHolder(View rootView) {
                TextView tv = new TextView(ContextActivity.this);
                tv.setTextColor(ResourcesFinder.getColor(getResources(),R.color.colorPrimaryDark));
                return new BaseViewHolder(tv);
            }

            @Override
            public void bindView(BaseViewHolder holder, int position, String s) {
                ((TextView)holder.itemView).setText(s);
            }

        });
        BottomSheetBehavior.from(findViewById(R.id.design_bottom_sheet)).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}
