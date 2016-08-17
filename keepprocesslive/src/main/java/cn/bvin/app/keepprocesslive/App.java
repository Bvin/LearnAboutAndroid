package cn.bvin.app.keepprocesslive;

import android.app.Activity;
import android.app.Application;

/**
 * Created by bvin on 2016/8/17.
 */

public class App extends Application {

    static App mApp;

    public static App self(){
        return mApp;
    }

    public Activity mOnePixelActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
