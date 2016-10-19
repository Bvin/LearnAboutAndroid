package cn.bvin.lib.base.text;

import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;

/**
 * Created by bvin on 2016/10/19.
 */

public class Ellipsize {

    /**
     * 连接字符串,全部容下尾部字符串，前面字符串过长则省略..显示
     * @param text text
     * @param behind behind
     * @param avail 可显示范围宽度，即TextView宽度
     * @param tp TextPaint
     * @param where TruncateAt
     * @return
     */
    public static CharSequence concat(CharSequence text, CharSequence behind, float avail, TextPaint tp, TextUtils.TruncateAt where) {
        float behindWidth = Layout.getDesiredWidth(behind, tp);
        CharSequence ellipsized = TextUtils.ellipsize(text, tp, avail - behindWidth, where);
        return TextUtils.concat(ellipsized, behind);
    }
}
