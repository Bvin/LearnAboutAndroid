package cn.rainbow.westore.activitylifecirclecallbacks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by bvin on 2017/1/10.
 */

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "App";

    int i;

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(this);
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: "+activity.toString());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "onActivityStarted: "+activity.toString());
        if (i==0){
            Log.d(TAG, "Resumed from background");
        }
        i++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "onActivityResumed: "+activity.toString());

    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "onActivityPaused: "+activity.toString());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, "onActivityStopped: "+activity.toString());
        i--;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "onActivityDestroyed: "+activity.toString());
    }
}
