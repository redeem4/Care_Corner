package com.carecorner;

import android.content.Intent;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class JournalRecyclerMainTest {
    private JournalRecyclerMain activity;
    private ArrayList<Journal> journalData = new ArrayList<Journal>();

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(JournalRecyclerMain.class)
                .create()
                .start()
                .resume()
                .get();

        journalData.add(new Journal("test 3/13/2021", "This is a test 1"));
        journalData.add(new Journal("test 3/14/2021", "This is a test 2"));
        journalData.add(new Journal("test 3/15/2021", "This is a test 3"));
        journalData.add(new Journal("test 3/16/2021", "This is a test 4"));
        journalData.add(new Journal("test 3/17/2021", "This is a test 5"));
    }

    @Test
    public void testOnItemClick() {
        ShadowActivity shadowActivity = shadowOf(activity);
        RecyclerView journalEntries = (RecyclerView) shadowActivity.getContentView().findViewById(R.id.recycler_view);
        activity.onItemClick(journalEntries, 0);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(JournalReader.class, shadowIntent.getIntentClass());
    }

    @Test
    public void testSaveAndLoadArrayList() {
        activity.saveArrayList(journalData, "test");
        ArrayList<Journal> temp = activity.loadArrayList("test");
        assertTrue(journalData.equals(temp));
    }

    @Test
    public void testFindJournalTitle() {
        assertTrue(activity.findJournalTitle(journalData, "test 3/14/2021"));
    }

    @Test
    public void testFindJournalText() {
        assertTrue(activity.findJournalText(journalData, "This is a test 4"));
    }

    @Test
    public void testGetIndexOfJournalTitle() {
        int index = activity.getIndexOfJournalTitle(journalData, "test 3/14/2021");
        assertEquals(1, index);
    }

    @Test
    public void testGetIndexOfJournalText() {
        int index = activity.getIndexOfJournalText(journalData, "This is a test 4");
        assertEquals(3, index);
    }

    //TODO: Write Tests for Add Journal Entry, Apply/Edit Texts, and Back Button.
}