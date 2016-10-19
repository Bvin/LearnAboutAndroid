package cn.rainbow.westore.fresco.fetcher;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.ProducerContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

import cn.rainbow.westore.fresco.consumer.ConsumerFetchState;
import cn.rainbow.westore.fresco.consumer.ProgressConsumer;

/**
 * Created by bvin on 2016/10/14.
 */

public class HurlNetworkFetcher extends BaseNetworkFetcher<ConsumerFetchState> {

    private ProgressConsumer<EncodedImage> mConsumerWrapper;

    public HurlNetworkFetcher(ProgressConsumer<EncodedImage> consumerWrapper) {
        mConsumerWrapper = consumerWrapper;
    }

    /**
     * Creates a new instance of the {@link FetchState}-derived object used to store state.
     *
     * @param consumer        the consumer
     * @param producerContext the producer's context
     * @return a new fetch state instance
     */
    @Override
    public ConsumerFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        HashSet<Consumer<EncodedImage>> consumers = new HashSet<>();
        consumers.add(consumer);
        consumers.add(mConsumerWrapper);
        return new ConsumerFetchState(consumers, producerContext);
    }

    /**
     * Initiates the network fetch and informs the producer when a response is received via the
     * provided callback.
     *
     * @param fetchState the fetch-specific state
     * @param callback   the callback used to inform the network fetch producer
     */
    @Override
    public void fetch(ConsumerFetchState fetchState, Callback callback) {
        try {
            URL url = new URL(fetchState.getUri().toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            mConsumerWrapper.onStart(fetchState, urlConnection.getContentLength());
            callback.onResponse(urlConnection.getInputStream(), urlConnection.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(e);
        }

    }
}
