package cn.bvin.lib.base.res;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by 32967 on 2016/3/18.
 */
public class ResourcesFinder {

    public static Drawable getDrawable(Resources res, int resId) {
        if (Build.VERSION.SDK_INT >= 22)
            return res.getDrawable(resId, null);
        else
            return res.getDrawable(resId);
    }

    public static int getColor(Resources res, int color) {
        if (Build.VERSION.SDK_INT > 22)
            return res.getColor(color, null);
        else
            return res.getColor(color);
    }
}
