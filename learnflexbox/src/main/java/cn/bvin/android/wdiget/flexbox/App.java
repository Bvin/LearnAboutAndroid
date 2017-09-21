package cn.bvin.android.wdiget.flexbox;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by bvin on 2017/9/21.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
