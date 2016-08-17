package cn.bvin.lib.checkboxon4x;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by bvin on 2016/8/15.
 */
public class CheckBoxComp extends LinearLayout{

    public CheckBoxComp(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context,R.layout.check_box_comp,this);
    }

    public CheckBoxComp(Context context) {
        super(context);
    }
}
