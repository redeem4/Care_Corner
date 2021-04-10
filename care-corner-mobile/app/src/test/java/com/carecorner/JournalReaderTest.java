package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
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
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class JournalReaderTest {
    private JournalReader activity;
    private Intent intent;

    @Before
    public void setUp() throws Exception
    {
        intent = new Intent();
        intent.putExtra("text", "My test journal entry");
        intent.putExtra("journalName", "My test title");

        activity = Robolectric.buildActivity(JournalReader.class, intent)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void checkJournalEntry() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        TextView textEntry = (TextView) shadowActivity.getContentView().findViewById(R.id.textBox);
        assertEquals(textEntry.getText().toString(), "My test journal entry");
    }

    @Test
    public void checkJournalEntryTitle() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        TextView titleEntry = (TextView) shadowActivity.getContentView().findViewById(R.id.title);
        assertEquals(titleEntry.getText().toString(), "My test title");
    }

    @Test
    public void checkEdit() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button btnEdit = (Button) shadowActivity.getContentView().findViewById(R.id.btnEdit);
        btnEdit.performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), "Sending to editor");
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(JournalEditorActivity.class, shadowIntent.getIntentClass());
    }
}
