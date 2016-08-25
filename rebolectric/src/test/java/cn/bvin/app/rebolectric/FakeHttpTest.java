package cn.bvin.app.rebolectric;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.httpclient.DefaultRequestDirector;
import org.robolectric.shadows.httpclient.FakeHttp;
import org.robolectric.shadows.httpclient.TestHttpResponse;

import java.io.IOException;

/**
 * Created by bvin on 2016/8/22.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class FakeHttpTest {

    @Test
    public void fakeHttp(){
        FakeHttp.setDefaultHttpResponse(404, "no such page");
        FakeHttp.addHttpResponseRule(HttpPost.METHOD_NAME, "http://some.uri",
                new TestHttpResponse(200, "a cheery response body"));
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                return 0;
            }
        };
        DefaultRequestDirector requestDirector = new DefaultRequestDirector(null, null, null, connectionKeepAliveStrategy, null, null, null, null, null, null, null, null);
        HttpResponse response = null;
        try {
            response = requestDirector.execute(null, new HttpGet("http://some.uri"), null);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
    }
}
