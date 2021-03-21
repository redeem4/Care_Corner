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
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowMediaPlayer;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class DialingActivityTest {
    private DialingActivity activity;
    private DialingActivity activity2;
    private Intent intent;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(DialingActivity.class)
                .create()
                .start()
                .resume()
                .get();

        intent = new Intent();
        intent.putExtra("callerName", "John Smith");
        intent.putExtra("callerPhoneNum", "(123)-456-7890");

        activity2 = Robolectric.buildActivity(DialingActivity.class, intent)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void checkingCallerIDAndPhoneNumberDefaultValues() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        TextView callerID = (TextView) shadowActivity.getContentView().findViewById(R.id.caller_id_text_2);
        TextView phoneNum = (TextView) shadowActivity.getContentView().findViewById(R.id.phone_number_text_2);
        assertEquals(callerID.getText().toString(), "Caller ID");
        assertEquals(phoneNum.getText().toString(), "Mobile: Phone Number Placeholder");
    }

    @Test
    public void checkingCallerIDAndPhoneNumberUserValues() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity2);
        TextView callerID = (TextView) shadowActivity.getContentView().findViewById(R.id.caller_id_text_2);
        TextView phoneNum = (TextView) shadowActivity.getContentView().findViewById(R.id.phone_number_text_2);
        assertEquals(callerID.getText().toString(), "John Smith");
        assertEquals(phoneNum.getText().toString(), "(123)-456-7890");
    }

    @Test
    public void checkingIfPanicButtonActivates() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity2);
        ImageButton btnEndCall = (ImageButton) shadowActivity.getContentView().findViewById(R.id.btnEndCall);
        btnEndCall.performLongClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), "Panic Button Activated!");
    }

    @Test
    public void emulatedVoiceServiceIsStarted(){

        ShadowActivity shadowActivity = shadowOf(activity2);
        /*  when DialingActivity is created, this test that the
            EmulatedVoiceService is also created. */
        Intent intent = Shadows.shadowOf(activity2).peekNextStartedService();
        ShadowMediaPlayer.CreateListener.
        assertEquals(EmulatedVoiceService.class.getCanonicalName(),intent.getComponent().getClassName());
    }
}