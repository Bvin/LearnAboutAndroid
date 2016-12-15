
## Usage
```xml
<GestureRefreshLayout>
    <ContentView />
    <RefreshView />
</GestureRefreshLayout>`
```

## Swipe Gesture
1. Both Translate, content and refresh view both translate, refresh view is next to content view transition.
2. Content Translate, just translate content view.
3. Refresher Translate, just translate refresh view.
4. Both Fixed, content and refresh view both are fixed.

---
中文版
## 用法
通常可以在GestureRefreshLayout布局里面添加子视图来实现刷新功能，第一个应为内容视图，第二个应为刷新视图，
不接受大于2个的子视图。

原生SwipeRefreshLayout的ChildView的宽高会强制match_parent，而我们的GestureRefreshLayout可以支持Child
View为wrap_content。

至于为何SRL（即SwipeRefreshLayout，以下通称SRL）会这样做，我猜测是因为SRL把触摸事件
从ChildView拦截到SRL自身去做事件处理，它原生是可以从ChildView的区域滑出到SRL自身的区域，Touche事件可
以无缝衔接，虽然...但是SRL和ChildView是没有间隙的，是严丝合缝的。而GRL（即GestureRefreshLayout，以下通
称GRL）的ContentView是可以支持wrap_content的，就算你的ChildView小到比TouchSlop还小，依然可以在
ChildView外的GRL区域起作用。


```xml
<GestureRefreshLayout>
    <ContentView />
    <RefreshView />
</GestureRefreshLayout>`
```

## 滑动手势
1. 同步位移，即刷新视图跟随内容纵向位移，适合宽屏刷新视图。
2. 悬浮位移，即内容视图固定，刷新视图跟着手势纵向位移，适合沉浸式刷新。
3. 内容下潜位移，刷新视图固定，内容视图跟着手势纵向位移，这种情况一般是刷新视图会有吸引人的动画。
4. 不位移，内容和刷新视图都不随手势位移，虽然纵向固定，但是可以通过其他形式来表现刷新行为。
