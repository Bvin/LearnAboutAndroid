package cn.bvin.app.rebolectric;

import android.content.Intent;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by bvin on 2016/8/19.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class MainActivityTest {


    @Test
    public void login() throws Exception {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.button).performClick();
        Intent intent = LoginActivity.getIntent(mainActivity);
        //assertThat(shadowOf(mainActivity).getNextStartedActivity()).isEqualTo(intent);
        Assert.assertEquals(shadowOf(mainActivity).getNextStartedActivity(),intent);
    }

}