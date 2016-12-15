FreeLine环境：

1. 安装Python 2.7，并加入环境变量
2. CompileSdk<=23，TargetSdk<=23, supportLibrary<=23
3. com.android.tools.build:gradle:2.1.2
4. 安装FreeLine插件

SwipeRefresh：

1. SwipeRefreshLayout宽高是match_parent，手动设置wrap_content无效
2. (旧版)子View必须是可Scroll的View
3. (旧版)一定要设置RefreshListener
4. 设置的颜色计划需要看时间保证，时间短的话颜色就不会完整切换

CoordinateLayout:

1. CoordinateLayout可以作为SwipeRefreshLayout的子View


AppBarLayout： 
AppBarLayout是实现了很多material designs 设计风格应用栏（即滑动手势）功能的一种竖向的线性布局。

1. AppBarLayout的子View应该指定layout_scrollFlags
2. AppBarLayout必须作为CoordinateLayout的直接子View，否则将很多特性将不支持
3. AppBarLayout需要滑动的View绑定AppBarLayout.ScrollingViewBehavior 来关联滑动行为

4. AppBarLayout的大部分作用都是处理Scroll，可以认为是在有scroll的情况才能体现它的作用

自定义View

1. 子类一定调用setMeasuredDimension方法，如果去掉了super.onMeasure()就要手动设置
2. 一定要measure过的View才能获取到measuredWidth/Height，getMeasuredWidth<<measure
3. layout过的View才能通过getWidth/height方法获取到真实的值, getWidth/Height<<layout
4. onInterceptTouchEvent返回false，往下传递，返回true就被拦截，点的子视图的时候。点到自己那部分还是
就传递到onTouchEvent了。

GestureRefresh

1. 点击子View是不被拦截的，但是如果从子View开始滑动，并且超过了TouchSlop将会拦截，当作是滑动刷新了。
2. 从InterceptTouchEvent的Down发到自己的TouchEvent，如果TouchEvent的Down返回true，事件就终止了。
3. InterceptTouchEvent返回true，传递到自身的TouchEvent;返回false,则不会传递到自身的TouchEvent，而是传递到子View
4. onTouchEvent返回true，即代表消费了，往下传递，false则不会继续往下传递，终止事件。
5. onTnterceptTouchEvent只会拦截在子View的区域，正常情况下事件是往下传递的，比如一个布局里面一个按钮
   点击它，这个按钮是可以收到的点击事件的。但是如果如果你要拦截这种往下传递的派发，也就是在onIntercept
   TouchEvent()方法中返回true，则投递给自身的onTouchEvent,子View是接受不到事件的。
   http://stackoverflow.com/questions/21288081/onintercepttouchevents-action-up-and-action-move-never-gets-called
