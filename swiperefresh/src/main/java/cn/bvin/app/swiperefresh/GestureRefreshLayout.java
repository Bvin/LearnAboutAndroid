package cn.bvin.app.swiperefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 手势刷新
 * Created by bvin on 2016/12/13.
 */

public class GestureRefreshLayout extends ViewGroup {
    
    private View mTarget;

    private static final int[] LAYOUT_ATTRS = new int[]{android.R.attr.enabled};

    public GestureRefreshLayout(Context context) {
        this(context, null);
    }

    public GestureRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(attrs, LAYOUT_ATTRS);
        setEnabled(a.getBoolean(0, true));
        a.recycle();
    }

    private void ensureTarget() {
        if (mTarget == null) {
            if (getChildCount() > 2) {
                throw new IllegalStateException("GestureRefreshLayout can host only 2 direct child");
            } else {
                mTarget = getChildAt(0);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
