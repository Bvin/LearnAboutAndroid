package cn.rainbow.westore.textview;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.bvin.lib.base.adapter.SimpleRecyclerAdapter;
import cn.bvin.lib.base.view.BaseViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"FZXH1JW.TTF");
        textView.setTypeface(typeface);

        textView.setText("123456789abcdefghijklmnopqrstuvwxyz");


        SpannableStringBuilder ssb = new SpannableStringBuilder(textView.getText());
        StyleSpan boldSpan = new StyleSpan( Typeface.BOLD );
        ssb.setSpan(boldSpan, 10, textView.getText().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE );
        //textView.setText(ssb);
    }

    class HorizontalScrollGoodsHolder extends BaseViewHolder {

        ImageView mSdvGoodsImage;
        TextView mTvGoodsName;
        TextView mTvGoodsPrice;
        TextView mTvOriginPrice;

        HorizontalScrollGoodsHolder(View convertView) {
            super(convertView);
            mSdvGoodsImage = (ImageView) convertView.findViewById(R.id.sdv_goods_image);
            mTvGoodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
            mTvGoodsPrice = (TextView) convertView.findViewById(R.id.tv_goods_price);
            mTvOriginPrice = (TextView) convertView.findViewById(R.id.tv_origin_price);
            mTvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }

        void text(TextView tv, String text){
            if (TextUtils.isEmpty(text)){
                clearText(tv);
            }else {
                setTextAndShow(tv, text);
            }
        }

        void setTextAndShow(TextView tv, String text){
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        }

        void clearTextAndHide(TextView tv){
            clearText(tv);
            tv.setVisibility(View.GONE);
        }

        void clearText(TextView tv) {
            tv.setText("");
        }

    }
}
