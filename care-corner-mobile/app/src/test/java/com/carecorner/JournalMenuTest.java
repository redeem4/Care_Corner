package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class JournalMenuTest {
    private JournalMenuActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(JournalMenuActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void homeButtonShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button btnHome = (Button) shadowActivity.getContentView().findViewById(R.id.btnHome);
        btnHome.performClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(MainMenuActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void viewButtonShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button btnView = (Button) shadowActivity.getContentView().findViewById(R.id.btnView);
        btnView.performClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(JournalRecyclerMain.class, shadowIntent.getIntentClass());
    }


}
