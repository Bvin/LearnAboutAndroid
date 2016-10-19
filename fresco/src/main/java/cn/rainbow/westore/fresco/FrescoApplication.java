package cn.rainbow.westore.fresco;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.FetchState;

import java.math.BigDecimal;

import cn.rainbow.westore.fresco.consumer.ProgressConsumer;
import cn.rainbow.westore.fresco.fetcher.HurlNetworkFetcher;


/**
 * Created by bvin on 2016/10/14.
 */

public class FrescoApplication extends Application {

    private static final String TAG = "FrescoApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setNetworkFetcher(new HurlNetworkFetcher(new ProgressConsumer<EncodedImage>() {

                    long size;

                    @Override
                    public void onStart(FetchState fetchState, long size) {
                        this.size = size;
                        Log.d(TAG, "onStart: ("+bytes2kb(size)+")"+fetchState.getUri().toString());
                    }

                    @Override
                    public void onNewResult(EncodedImage newResult, boolean isLast) {
                        Log.d(TAG, "onNewResult: "+bytes2kb(newResult.getSize()));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }

                    @Override
                    public void onCancellation() {

                    }

                    @Override
                    public void onProgressUpdate(float progress) {
                        Log.d(TAG, "onProgressUpdate: "+bytes2kb(progress*size));
                    }
                })).build();
        Fresco.initialize(this, config);
    }

    /**
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
     *
     * @param bytes
     * @return
     */
    public static String bytes2kb(float bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }
}
