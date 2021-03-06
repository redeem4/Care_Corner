package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class WelcomeActivityTest {
    private WelcomeActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(WelcomeActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void loginButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnLoginMenu);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(LoginActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void resourcesButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnResources);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(ResourceActivity.class, shadowIntent.getIntentClass());
    }
}