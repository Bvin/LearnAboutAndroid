package cn.rainbow.westore.fresco.consumer;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;

/**
 * Created by bvin on 2016/10/14.
 */

public interface ProgressConsumer<T> extends Consumer<T> {

    void onStart(FetchState fetchState, long size);
}
