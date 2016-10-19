package cn.rainbow.westore.fresco.consumer;

import com.facebook.imagepipeline.producers.Consumer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bvin on 2016/10/14.
 */
public class ConsumerWrapper<T> implements Consumer<T> {

    private Set<Consumer<T>> mConsumers;

    public ConsumerWrapper(Set<Consumer<T>> consumers) {
        this.mConsumers = consumers;
        /*for (Consumer<T> consumer :
                consumers) {
            mConsumers.add(consumer);
        }*/
    }

    /**
     * Called by a producer whenever new data is produced. This method should not throw an exception.
     * <p>
     * <p> In case when result is closeable resource producer will close it after onNewResult returns.
     * Consumer needs to make copy of it if the resource must be accessed after that. Fortunately,
     * with CloseableReferences, that should not impose too much overhead.
     *
     * @param newResult
     * @param isLast    true if newResult is the last result
     */
    @Override
    public void onNewResult(T newResult, boolean isLast) {
        if (mConsumers != null) {
            for (Consumer<T> consumer :
                    mConsumers) {
                consumer.onNewResult(newResult, isLast);
            }
        }
    }

    /**
     * Called by a producer whenever it terminates further work due to Throwable being thrown. This
     * method should not throw an exception.
     *
     * @param t
     */
    @Override
    public void onFailure(Throwable t) {
        for (Consumer<T> consumer :
                mConsumers) {
            consumer.onFailure(t);
        }
    }

    /**
     * Called by a producer whenever it is cancelled and won't produce any more results
     */
    @Override
    public void onCancellation() {
        for (Consumer<T> consumer :
                mConsumers) {
            consumer.onCancellation();
        }
    }

    /**
     * Called when the progress updates.
     *
     * @param progress in range [0, 1]
     */
    @Override
    public void onProgressUpdate(float progress) {
        for (Consumer<T> consumer :
                mConsumers) {
            consumer.onProgressUpdate(progress);
        }
    }
}
