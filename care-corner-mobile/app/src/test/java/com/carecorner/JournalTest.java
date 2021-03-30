package com.carecorner;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JournalTest {
    private Journal journal;

    @Before
    public void setUp() throws Exception
    {
        journal = new Journal("My Journal", "This is a test.");
    }

    @Test
    public void testGetName() {
        String name = journal.getName();
        assertEquals(name, "My Journal");
    }

    @Test
    public void testSetName() {
        journal.setName("My Journal 2");
        assertEquals(journal.getName(), "My Journal 2");
    }

    @Test
    public void testGetText() {
        String text = journal.getText();
        assertEquals(text, "This is a test.");
    }

    @Test
    public void testSetText() {
        journal.setText("This is a test. Testing Some More.");
        assertEquals(journal.getText(), "This is a test. Testing Some More.");
    }

    @Test
    public void testEquals() {
        Journal temp1 = new Journal("test", "testing");
        assertFalse(journal.equals(temp1));
        Journal temp2 = journal;
        assertTrue(journal.equals(temp2));
    }

    @Test
    public void testToString() {
        String temp = journal.toString();
        assertEquals(temp, "Title:" + " " + journal.getName() + "/n"
                                + "Text:" + " " + journal.getText() + "/n");
    }
}