package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import static org.robolectric.Shadows.shadowOf;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class JournalEditorActivityTest {
    private JournalEditorActivity activity;
    private Intent intent;

    @Before
    public void setUp() throws Exception
    {
        intent = new Intent();
        intent.putExtra("text", "My test journal entry");
        intent.putExtra("title", "My test title")

        activity = Robolectric.buildActivity(DialingActivity.class, intent)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void checkTextBox() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        EditText textEntry = (EditText) shadowActivity.getContentView().findViewById(R.id.textEntryBox);
        EditText titleEntry = (EditText) shadowActivity.getContentView().findViewById(R.id.titleBox);
        assertEquals(textEntry.getText().toString(), "My test journal entry");
        assertEquals(titleEntry.getText.toString(), "My test title");
    }

    @Test
    public void checkSave() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button btnSave = (Button) shadowActivity.getContentView().findViewById(R.id.btnSave);
        btnSave.performLongClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), "Entry saved");
    }

    @Test
    public void checkExit() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button btnExit = (Button) shadowActivity.getContentView().findViewById(R.id.btnExit);
        btnExit.performLongClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), "Exiting");
    }

    //checkExitWithoutSaving
    //checkIfSaveWorksProperly
    //checkClickOnTextBox
}
