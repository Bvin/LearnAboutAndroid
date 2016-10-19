package cn.rainbow.westore.fresco.consumer;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.ProducerContext;

import java.util.Set;

/**
 * Created by bvin on 2016/10/14.
 */

public class ConsumerFetchState extends FetchState {


    public ConsumerFetchState(Set<Consumer<EncodedImage>> consumers, ProducerContext context) {
        super(new ConsumerWrapper<>(consumers), context);
    }

}
