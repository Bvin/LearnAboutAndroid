package cn.bvin.android.app.calculator.ground;

import android.app.Application;

import com.baidu.mobstat.StatService;

/**
 * Created by bvin on 2017/6/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StatService.autoTrace(this, true, false);
    }
}
