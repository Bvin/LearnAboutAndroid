package cn.bvin.app.designlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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

public class CoordinatorActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CoordinatorActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("第"+i+"item");
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SimpleRecyclerAdapter<String,BaseViewHolder>(data) {

            @Override
            public BaseViewHolder createViewHolder(View rootView) {
                TextView tv = new TextView(CoordinatorActivity.this);
                tv.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,200));
                tv.setTextColor(ResourcesFinder.getColor(getResources(),R.color.colorPrimaryDark));
                return new BaseViewHolder(tv);
            }

            @Override
            public void bindView(BaseViewHolder holder, int position, String s) {
                ((TextView)holder.itemView).setText(s);
            }

        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Too long long long!",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
