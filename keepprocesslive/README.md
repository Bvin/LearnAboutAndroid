# 前台进程(Foreground process)
# 可见进程(Visible process)
# 服务进程(Service process)
# 后台进程(Background process)
# 空进程(Empty process)
Android 中对于内存的回收，主要依靠 Lowmemorykiller 来完成，是一种根据 OOM_ADJ 阈值级别触发相应力度的内存回收的机制。
综上，可以得出减少进程被杀死概率无非就是想办法提高进程优先级，减少进程在内存不足等情况下被杀死的概率。
>http://mp.weixin.qq.com/s?__biz=MzA3NTYzODYzMg==&mid=2653577617&idx=1&sn=623256a2ff94641036a6c9eea17baab8&scene=0#wechat_redirect