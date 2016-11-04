notes:gradle只支持res-auto命名空间，不支持自定义了
1.BottomNavigationView3个item文字可全部显示，大于3个就只有选中的才显示
2.design库和RecyclerView在version25预览时会报错Exception raised during rendering: Unable to locate mode 0，
解决：把依赖版本降为24.2.1