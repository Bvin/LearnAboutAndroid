package cn.bvin.app.designlibrary.coordinator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

import java.util.ArrayList;
import java.util.List;

import cn.bvin.app.designlibrary.R;
import cn.bvin.lib.base.adapter.SimpleRecyclerAdapter;
import cn.bvin.lib.base.res.ResourcesFinder;
import cn.bvin.lib.base.view.BaseViewHolder;

public class ItemTouchHelperActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ItemTouchHelperActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_helper);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        final List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        recyclerView.setAdapter(new SimpleRecyclerAdapter<String, TouchHelperHolder>(this,R.layout.item_touch_helper,data) {

            @Override
            public TouchHelperHolder createViewHolder(View rootView) {
                return new TouchHelperHolder(rootView);
            }

            @Override
            public void bindView(TouchHelperHolder holder, int position, String s) {
                holder.mItemContent.setText(s);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            private float swipeThreshold;
            private boolean disable;

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //drag
                return false;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                if (disable) return false;
                return super.isItemViewSwipeEnabled();
            }

            @Override
            public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
                if (swipeThreshold>0) return swipeThreshold;
                return super.getSwipeThreshold(viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //swipe
                int pos = viewHolder.getAdapterPosition();
                data.remove(pos);
                recyclerView.getAdapter().notifyItemRemoved(pos);
            }

            private boolean hasWidth;

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Log.d("onChildDraw: ",dX+","+((TouchHelperHolder)viewHolder).mItemAction.getWidth());
                /*if (Math.abs(dX)<=((TouchHelperHolder)viewHolder).mItemAction.getWidth()){
                    &&((TouchHelperHolder)viewHolder).mItemAction.getWidth()>0
                    getDefaultUIUtil().onDraw(c,recyclerView,((TouchHelperHolder)viewHolder).mItemContent,dX,dY,actionState,isCurrentlyActive);
                    if (dX < 0){
                        ((TouchHelperHolder)viewHolder).mItemAction.setVisibility(View.VISIBLE);
                    }
                }*/
                /*
                ViewCompat.setTranslationX(((TouchHelperHolder)viewHolder).mItemContent,dX);
                if (dX < 0){
                    ((TouchHelperHolder)viewHolder).mItemAction.setVisibility(View.VISIBLE);
                }*/
                hasWidth = ((TouchHelperHolder)viewHolder).mItemAction.getWidth()>0;
                if (hasWidth){
                    swipeThreshold = ((TouchHelperHolder)viewHolder).mItemAction.getWidth()/viewHolder.itemView.getWidth();
                    if (Math.abs(dX)>((TouchHelperHolder)viewHolder).mItemAction.getWidth()){
                        disable = true;
                        return;
                    }
                }
                ViewCompat.setTranslationX(((TouchHelperHolder)viewHolder).mItemContent,dX);
                if (dX < 0){
                    ((TouchHelperHolder)viewHolder).mItemAction.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                getDefaultUIUtil().clearView(((TouchHelperHolder)viewHolder).mItemContent);
                ((TouchHelperHolder)viewHolder).mItemAction.setVisibility(View.GONE);
            }

            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                getDefaultUIUtil().onDrawOver(c,recyclerView,((TouchHelperHolder)viewHolder).mItemContent,dX,dY,actionState,isCurrentlyActive);
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (viewHolder != null) {
                    getDefaultUIUtil().onSelected(((TouchHelperHolder)viewHolder).mItemContent);
                }
            }
        });

        itemTouchHelper = new ItemTouchHelperExtension.SimpleCallback();
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    class TouchHelperHolder extends BaseViewHolder{

        TextView mItemContent;
        TextView mItemAction;

        public TouchHelperHolder(View itemView) {
            super(itemView);
            mItemContent = (TextView) itemView.findViewById(R.id.item_content);
            mItemAction = (TextView) itemView.findViewById(R.id.item_action);
        }
    }
}
