package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.ImageButton;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class CallingActivityTest {
    private CallingActivity activity;
    private CallingActivity activity2;
    private Intent intent;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(CallingActivity.class)
                .create()
                .start()
                .resume()
                .get();

        intent = new Intent();
        intent.putExtra("callerName", "John Smith");
        intent.putExtra("callerPhoneNum", "(123)-456-7890");

        activity2 = Robolectric.buildActivity(CallingActivity.class, intent)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void acceptCallButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnAcceptCall);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(DialingActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void rejectCallButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        ImageButton button = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnRejectCall);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(FakePhoneCallMenuActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void checkingCallerIDAndPhoneNumberDefaultValues() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        TextView callerID = (TextView) shadowActivity.getContentView().findViewById(R.id.caller_id_text);
        TextView phoneNum = (TextView) shadowActivity.getContentView().findViewById(R.id.phone_number_text);
        assertEquals(callerID.getText().toString(), "Caller ID");
        assertEquals(phoneNum.getText().toString(), "Mobile: Phone Number Placeholder");
    }

    @Test
    public void checkingCallerIDAndPhoneNumberUserValues() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity2);
        TextView callerID = (TextView) shadowActivity.getContentView().findViewById(R.id.caller_id_text);
        TextView phoneNum = (TextView) shadowActivity.getContentView().findViewById(R.id.phone_number_text);
        assertEquals(callerID.getText().toString(), "John Smith");
        assertEquals(phoneNum.getText().toString(), "(123)-456-7890");
    }
}