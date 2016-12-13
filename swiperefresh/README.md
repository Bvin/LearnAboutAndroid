
## Usage
```xml
<SwipeRefreshLayout>
    <ContentView />
    <RefreshView />
</SwipeRefreshLayout>`
```

## Swipe Gesture
1. Both Translate, content and refresh view both translate, refresh view is next to content view transition.
2. Content Translate, just translate content view.
3. Refresher Translate, just translate refresh view.
4. Both Fixed, content and refresh view both are fixed.

---
中文版
## 用法
通常可以在SwipeRefreshLayout布局里面添加子视图来实现刷新功能，第一个应为内容视图，第二个应为刷新视图，
不接受大于2个的子视图。

```xml
<SwipeRefreshLayout>
    <ContentView />
    <RefreshView />
</SwipeRefreshLayout>`
```

## 滑动手势
1. 同步位移，即刷新视图跟随内容纵向位移，适合宽屏刷新视图。
2. 悬浮位移，即内容视图固定，刷新视图跟着手势纵向位移，适合沉浸式刷新。
3. 内容下潜位移，刷新视图固定，内容视图跟着手势纵向位移，这种情况一般是刷新视图会有吸引人的动画。
4. 不位移，内容和刷新视图都不随手势位移，虽然纵向固定，但是可以通过其他形式来表现刷新行为。
