package cn.rainbow.westore.textview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by bvin on 2016/10/13.
 * 左边推移Spacing
 */

public class HorizontalRecyclerSpacing extends RecyclerView.ItemDecoration {

    private int mSpacing;
    private int mLeftSpacing;
    private int mRightSpacing;

    public HorizontalRecyclerSpacing(int spacing, int leftSpacing, int rightSpacing) {
        mSpacing = spacing;
        mLeftSpacing = leftSpacing;
        mRightSpacing = rightSpacing;
    }

    public HorizontalRecyclerSpacing(int spacing) {
        this(spacing, 0, 0);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        
        int position = parent.getChildPosition(view);

        if (position == 0) {
            outRect.left = mLeftSpacing;
        } else {
            outRect.left = mSpacing;
        }

        if (position == parent.getLayoutManager().getItemCount() - 1) {
            outRect.right = mRightSpacing;
        }
    }
}
