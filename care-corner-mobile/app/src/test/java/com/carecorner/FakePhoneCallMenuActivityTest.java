package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
import org.robolectric.shadows.ShadowLooper;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class FakePhoneCallMenuActivityTest {
    private FakePhoneCallMenuActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(FakePhoneCallMenuActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void callButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnCall);
        button.callOnClick();
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(CallingActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void setButtonShouldSaveValues() throws Exception
    {
        EditText nameEntryBox = (EditText) activity.findViewById(R.id.nameEntryBox);
        EditText phoneEntryBox = (EditText) activity.findViewById(R.id.phoneEntryBox);
        Spinner spinnerWaitTime = (Spinner) activity.findViewById(R.id.spinnerWaitTime);
        Spinner spinnerVoiceSelector = (Spinner) activity.findViewById(R.id.spinnerVoiceSelector);

        nameEntryBox.setText("John Smith");
        phoneEntryBox.setText("7571234567");
        spinnerWaitTime.setSelection(0);
        spinnerVoiceSelector.setSelection(0);

        ShadowActivity shadowActivity = shadowOf(activity);
        EditText nameEntryBox2 = (EditText) shadowActivity.getContentView().findViewById(R.id.nameEntryBox);
        EditText phoneEntryBox2 = (EditText) shadowActivity.getContentView().findViewById(R.id.phoneEntryBox);
        Spinner spinnerWaitTime2 = (Spinner) shadowActivity.getContentView().findViewById(R.id.spinnerWaitTime);
        Spinner spinnerVoiceSelector2 = (Spinner) shadowActivity.getContentView().findViewById(R.id.spinnerVoiceSelector);

        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnSet);
        button.callOnClick();

        assertEquals(nameEntryBox.getText().toString(), nameEntryBox2.getText().toString());
        assertEquals(phoneEntryBox.getText().toString(), phoneEntryBox2.getText().toString());
        assertEquals(spinnerWaitTime.getSelectedItem(), spinnerWaitTime2.getSelectedItem());
        assertEquals(spinnerVoiceSelector.getSelectedItem(), spinnerVoiceSelector2.getSelectedItem());
    }
}