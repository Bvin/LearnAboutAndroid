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