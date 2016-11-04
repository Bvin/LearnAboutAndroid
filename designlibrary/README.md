notes:gradle只支持res-auto命名空间，不支持自定义了
<p>1.BottomNavigationView3个item文字可全部显示，大于3个就只有选中的才显示
<p>2.design库和RecyclerView在version25预览时会报错Exception raised during rendering: Unable to locate mode 0，
<br>解决：把依赖版本降为24.2.1
<p>3.behavior一定要override含有AttributeSet的构造方法，要不然布局会加载出错
<p>4.AppbarLayout的划动属性layout_scroll_frags
<ol>
    <li>scroll:可划动(直邮申明此属性的才有以下才有效果)
    <li>enterAlways:上滑隐藏,下滑可见
    <li>enterAlwaysCollapsed:下滑一直收缩，minHeight部分可见，滑到顶部才展开全部
    <li>exitUntilCollapsed：上滑时，当展开的View被收起时才会滚动recyclerView
    <li>snap:具有粘性，离到很近的时候自动贴过去
</ol>
<p>5.md语法，\<p\>是另起一行,\<br\>是同一段落里换行
        