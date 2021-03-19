package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageButton;

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

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class MainMenuActivityTest {

    private MainMenuActivity activity;
    //TODO: Finish writing tests once the associated activities exist.

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(MainMenuActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void fakePhoneCallButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnFakePhoneCall);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(FakePhoneCallMenuActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void mombotCallButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnMomBot);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
//        assertEquals(Mombot.class, shadowIntent.getIntentClass());
    }

    @Test
    public void safewalkButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnSafeWalk);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
//        assertEquals(SafeWalk.class, shadowIntent.getIntentClass());
    }

    @Test
    public void journalButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnJournal);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
//        assertEquals(JournalMenu.class, shadowIntent.getIntentClass());
    }

    @Test
    public void resourcesButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnResourcesMenu);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(ResourceActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void reportingAssistanceButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnReportingAssistance);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
//        assertEquals(ReportingAssistanceMenu.class, shadowIntent.getIntentClass());
    }
}