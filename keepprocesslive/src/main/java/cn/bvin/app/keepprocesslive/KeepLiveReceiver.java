package cn.bvin.app.keepprocesslive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bvin on 2016/8/17.
 */

public class KeepLiveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            if(App.self().mOnePixelActivity!=null) App.self().mOnePixelActivity.finish();
        }else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            OnePixelActivity.start(context);
        }
        OnePixelActivity.start(context);
    }
}
