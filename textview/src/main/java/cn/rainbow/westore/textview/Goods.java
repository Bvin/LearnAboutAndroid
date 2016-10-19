package cn.rainbow.westore.textview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bvin on 2016/10/18.
 */

public class Goods {

    public static class Item{

        /**
         * goods_id : 1390582
         * source : ht
         * source_id : 841065
         * goods_name : 2包装 日本花王拉拉裤XL24片
         * goods_img : http://img1.tianhong.cn/upload/pd/m/yyml_3389/20166/41ee51738dd94b50bfb91a9c419eb316_600x600.jpg
         * sale_price : ￥149
         * market_price : ￥299
         * total_stock : 134
         * country_image : http://img1.tianhong.cn/upload/mimage/country/20160203104939.png
         * sale_type_name :
         * transport_type_name : 保税区自发货
         */

        @SerializedName("goods_id")
        private long mGoodsId;

        @SerializedName("source")
        private String mSource;

        @SerializedName("source_id")
        private String mSourceId;

        @SerializedName("goods_name")
        private String mGoodsName;

        @SerializedName("goods_img")
        private String mGoodsImg;

        @SerializedName("sale_price")
        private String mSalePrice;

        @SerializedName("market_price")
        private String mMarketPrice;

        @SerializedName("total_stock")
        private int mTotalStock;

        @SerializedName("country_image")
        private String mCountryImage;

        @SerializedName("sale_type_name")
        private String mSaleTypeName;

        @SerializedName("transport_type_name")
        private String mTransportTypeName;

        public long getGoodsId() {
            return mGoodsId;
        }

        public String getSource() {
            return mSource;
        }

        public String getSourceId() {
            return mSourceId;
        }

        public String getGoodsName() {
            return mGoodsName;
        }

        public String getGoodsImg() {
            return mGoodsImg;
        }

        public String getSalePrice() {
            return mSalePrice;
        }

        public String getMarketPrice() {
            return mMarketPrice;
        }

        public int getTotalStock() {
            return mTotalStock;
        }

        public String getCountryImage() {
            return mCountryImage;
        }

        public String getSaleTypeName() {
            return mSaleTypeName;
        }

        public String getTransportTypeName() {
            return mTransportTypeName;
        }

    }

}
