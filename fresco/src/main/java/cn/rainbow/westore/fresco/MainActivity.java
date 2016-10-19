package cn.rainbow.westore.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.getImagePipeline().clearCaches();
        String url = "http://imgtest.tianhong.cn/upload/mimage/contract/201610/b7c3997c-a3eb-48b1-a0fe-49226f14cb33.jpg";
        SimpleDraweeView sdv = ((SimpleDraweeView) findViewById(R.id.sdv));
        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(
                        new ResizeOptions(500,500,
                                sdv.getLayoutParams().height));
        // Create the Builder
        PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequestBuilder.build());
        sdv.setController(builder.build());
    }
}
