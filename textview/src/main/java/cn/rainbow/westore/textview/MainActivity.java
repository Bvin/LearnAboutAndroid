package cn.rainbow.westore.textview;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

        String gsonString = "[{\"goods_id\":1358243,\"source\":\"bbc\",\"source_id\":\"837262\",\"goods_name\":\"\\u4e50\\u89c6\\uff08Letv\\uff09\\u4e502 \\u53cc\\u5361\\u53cc\\u5f85 4G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20165\\/5a090746d5cc4ed4b52c6d95b67704e0_600x600.jpg\",\"sale_price\":\"\\uffe51199-\\uffe51299\",\"market_price\":\"\\uffe51399\",\"total_stock\":34,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":1360297,\"source\":\"bbc\",\"source_id\":\"837491\",\"goods_name\":\"\\u534e\\u4e3a \\uff08HUAWEI\\uff09\\u8363\\u8000\\u7545\\u73a95C \\u53cc\\u5361\\u53cc\\u5f85 4G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20165\\/6b0171c18789496b94c313419a122e5c_600x600.jpg\",\"sale_price\":\"\\uffe51099-\\uffe51399\",\"market_price\":\"\\uffe51299\",\"total_stock\":220,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":939642,\"source\":\"bbc\",\"source_id\":\"794807\",\"goods_name\":\"\\u82f9\\u679c iPhone 6S Plus 64GB \\u5168\\u7f51\\u901a \\u79fb\\u52a8\\u8054\\u901a\\u7535\\u4fe14G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20161\\/9c676184849d45018c6b5612ef10fb9a_600x600.jpg\",\"sale_price\":\"\\uffe55899-\\uffe56099\",\"market_price\":\"\\uffe57299\",\"total_stock\":57,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":939648,\"source\":\"bbc\",\"source_id\":\"794805\",\"goods_name\":\"\\u82f9\\u679c iPhone 6S 128GB \\u5168\\u7f51\\u901a \\u79fb\\u52a8\\u8054\\u901a\\u7535\\u4fe14G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20161\\/c0a02fed17ab44609fe3ba82f972ee06_600x600.jpg\",\"sale_price\":\"\\uffe55899\",\"market_price\":\"\\uffe57399\",\"total_stock\":40,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":1360298,\"source\":\"bbc\",\"source_id\":\"837493\",\"goods_name\":\"\\u534e\\u4e3a \\uff08HUAWEI\\uff09P9 Plus \\u53cc\\u5361\\u53cc\\u5f85 4G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20165\\/97260ea97ef842aa99acbc718c07b55a_600x600.jpg\",\"sale_price\":\"\\uffe54399-\\uffe54899\",\"market_price\":\"\\uffe54599\",\"total_stock\":36,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":1541727,\"source\":\"bbc\",\"source_id\":\"856844\",\"goods_name\":\"\\u5c0f\\u7c73 \\u7ea2\\u7c733S \\u53cc\\u5361\\u53cc\\u5f854G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20168\\/bb300b8706214bfdad5d700b975bfd42_600x600.jpg\",\"sale_price\":\"\\uffe5859-\\uffe51059\",\"market_price\":\"\\uffe51059\",\"total_stock\":109,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":1265461,\"source\":\"bbc\",\"source_id\":\"823308\",\"goods_name\":\"\\u534e\\u4e3a\\uff08HUAWEI\\uff09 P9 \\u53cc\\u5361\\u53cc\\u5f85 4G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20164\\/2ee807fbc1434f319648fcd19a4a1b42_600x600.jpg\",\"sale_price\":\"\\uffe53199-\\uffe54099\",\"market_price\":\"\\uffe54499\",\"total_stock\":131,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":1360296,\"source\":\"bbc\",\"source_id\":\"837492\",\"goods_name\":\"\\u534e\\u4e3a \\uff08HUAWEI\\uff09\\u8363\\u8000V8 \\u53cc\\u5361\\u53cc\\u5f85\\u53cc\\u901a 4G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20165\\/6dc827652b114b189ad54aa901af799d_600x600.jpg\",\"sale_price\":\"\\uffe52699-\\uffe53399\",\"market_price\":\"\\uffe53599\",\"total_stock\":129,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"},{\"goods_id\":1541723,\"source\":\"bbc\",\"source_id\":\"856846\",\"goods_name\":\"\\u534e\\u4e3a \\u8363\\u80008  \\u53cc\\u5361\\u53cc\\u5f854G\\u624b\\u673a\",\"goods_img\":\"http:\\/\\/img1.tianhong.cn\\/upload\\/pd\\/m\\/shouji\\/20168\\/4b2b8281d65e4b33831f59d460f057df_600x600.jpg\",\"sale_price\":\"\\uffe52399-\\uffe53399\",\"market_price\":\"\\uffe52899\",\"total_stock\":108,\"country_image\":\"\",\"sale_type_name\":\"\",\"transport_type_name\":\"\"}]";
        List<Goods.Item> goods = new Gson().fromJson(gsonString,new TypeToken<List<Goods.Item>>(){}.getType());
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv.addItemDecoration(new HorizontalRecyclerSpacing((int) getResources().getDimension(R.dimen.spacing)));
        rv.setAdapter(new SimpleRecyclerAdapter<Goods.Item,HorizontalScrollGoodsHolder>(this,R.layout.horizontal_scroll_goods_item,goods) {

            /**
             * 创建具体ViewHolder.
             *
             * @param rootView adapter root view，如果构造方法没传layoutId和Context将会是null值
             * @return 具体ViewHolder.
             */
            @Override
            public HorizontalScrollGoodsHolder createViewHolder(View rootView) {
                return new HorizontalScrollGoodsHolder(rootView);
            }

            @Override
            public void bindView(HorizontalScrollGoodsHolder holder, int position, Goods.Item item) {
                if (item != null) {

                    holder.text(holder.mTvGoodsName, item.getGoodsName());

                    holder.text(holder.mTvGoodsPrice, item.getSalePrice());

                    holder.clearTextAndHide(holder.mTvOriginPrice);//先隐藏
                    if (!TextUtils.isEmpty(item.getMarketPrice()) && !item.getMarketPrice().equalsIgnoreCase("null")) {
                        if (item.getSalePrice().equalsIgnoreCase(item.getMarketPrice())) {
                            holder.setTextAndShow(holder.mTvOriginPrice, item.getMarketPrice());
                        }
                    }
                }
            }
        });
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
