package cn.rainbow.westore.imagespan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);

        String tag1 = "自营";
        String tag2 = "保税区自发货";

        SpannableStringBuilder ssb = new SpannableStringBuilder();

        SpannableStringBuilder sbLeft = new SpannableStringBuilder(tv.getText().subSequence(0,tv.getText().length()/2));
        sbLeft.setSpan(new LeftTagSpan(),0,sbLeft.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.append(sbLeft);

        SpannableStringBuilder sbRight = new SpannableStringBuilder(tv.getText().subSequence(tv.getText().length()/2,tv.getText().length()));
        sbRight.setSpan(new RightTagSpan(),0,sbRight.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.append(sbRight);

        //ssb.setSpan(new RightTagSpan(),ssb.length()/2,ssb.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(ssb);
    }
}
