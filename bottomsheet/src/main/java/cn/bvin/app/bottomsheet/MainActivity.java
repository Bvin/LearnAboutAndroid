package cn.bvin.app.bottomsheet;

import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bvin.lib.base.adapter.SimpleRecyclerAdapter;
import cn.bvin.lib.base.view.BaseViewHolder;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SimpleRecyclerAdapter<String,BaseViewHolder>(data) {

            @Override
            public BaseViewHolder createViewHolder(View rootView) {
                TextView tv = new TextView(MainActivity.this);
                return new BaseViewHolder(tv);
            }

            @Override
            public void bindView(BaseViewHolder holder, int position, String s) {
                ((TextView)holder.itemView).setText(s);
            }

        });

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(recyclerView);
        recyclerView.measure(0, 0);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomSheetDialog.show();
                ContextActivity.start(MainActivity.this);
            }
        });

    }
}
