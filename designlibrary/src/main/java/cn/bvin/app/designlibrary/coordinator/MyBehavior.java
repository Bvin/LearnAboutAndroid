package cn.bvin.app.designlibrary.coordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by bvin on 2016/11/4.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<View> {

    private static final String TAG = "MyBehavior";

    private int mScrollDistance;

    /**
     * Default constructor for inflating Behaviors from layout. The Behavior will have
     * the opportunity to parse specially defined layout parameters. These parameters will
     * appear on the child view tag.
     *
     * @param context
     * @param attrs
     */
    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Called when a descendant of the CoordinatorLayout attempts to initiate a nested scroll.
     * <p>
     * <p>Any Behavior associated with any direct child of the CoordinatorLayout may respond
     * to this event and return true to indicate that the CoordinatorLayout should act as
     * a nested scrolling parent for this scroll. Only Behaviors that return true from
     * this method will receive subsequent nested scroll events.</p>
     *
     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
     *                          associated with
     * @param child             the child view of the CoordinatorLayout this Behavior is associated with
     * @param directTargetChild the child view of the CoordinatorLayout that either is or
     *                          contains the target of the nested scroll operation
     * @param target            the descendant view of the CoordinatorLayout initiating the nested scroll
     * @param nestedScrollAxes  the axes that this nested scroll applies to. See
     *                          {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                          {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return true if the Behavior wishes to accept this nested scroll
     * @see NestedScrollingParent#onStartNestedScroll(View, View, int)
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (ViewCompat.SCROLL_AXIS_VERTICAL & nestedScrollAxes)!=0;
    }

    /**
     * Called when a nested scroll in progress is about to update, before the target has
     * consumed any of the scrolled distance.
     * <p>
     * <p>Any Behavior associated with the direct child of the CoordinatorLayout may elect
     * to accept the nested scroll as part of {@link #onStartNestedScroll}. Each Behavior
     * that returned true will receive subsequent nested scroll events for that nested scroll.
     * </p>
     * <p>
     * <p><code>onNestedPreScroll</code> is called each time the nested scroll is updated
     * by the nested scrolling child, before the nested scrolling child has consumed the scroll
     * distance itself. <em>Each Behavior responding to the nested scroll will receive the
     * same values.</em> The CoordinatorLayout will report as consumed the maximum number
     * of pixels in either direction that any Behavior responding to the nested scroll reported
     * as consumed.</p>
     *
     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
     *                          associated with
     * @param child             the child view of the CoordinatorLayout this Behavior is associated with
     * @param target            the descendant view of the CoordinatorLayout performing the nested scroll
     * @param dx                the raw horizontal number of pixels that the user attempted to scroll
     * @param dy                the raw vertical number of pixels that the user attempted to scroll
     * @param consumed          out parameter. consumed[0] should be set to the distance of dx that
     *                          was consumed, consumed[1] should be set to the distance of dy that
     *                          was consumed
     * @see NestedScrollingParent#onNestedPreScroll(View, int, int, int[])
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        Log.d(TAG, "onNestedPreScroll: "+dy);
        if (dy < 0&&mScrollDistance>0 || dy > 0&&mScrollDistance<0){
            mScrollDistance = 0;
        }
        mScrollDistance += dy;
        boolean isOverChildHeight = mScrollDistance>child.getHeight();

        if (mScrollDistance>child.getHeight()){
            child.setVisibility(View.GONE);
        }else if(mScrollDistance<child.getHeight()){
            child.setVisibility(View.VISIBLE);
        }
    }
}
