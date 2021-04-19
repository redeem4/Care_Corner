package com.carecorner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Journal {
    private String name;
    private String text;
    private String date;
    private String time;

    Calendar calendar = new GregorianCalendar();
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    /**
     * Default Constructor for the Journal Class.
     * @param  name  The name of the Journal
     * @param  text The text of the Journal
     */
    public Journal(String name, String text)
    {
        this.name = name;
        this.text = text;
        this.date = month + "/" + dayOfMonth + "/" + year;
        this.time = hour + ":" + minute;
    }

    /**
     * Overloaded Constructor for the Journal Class for setting the time/date.
     * @param  name The name of the Journal
     * @param  text The text of the Journal
     * @param  date The date of the Journal
     * @param  time The time of the Journal
     */
    public Journal(String name, String text, String date, String time)
    {
        this.name = name;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Journal j1 = (Journal) obj;

        return this.name.equals(j1.name) && this.text.equals(j1.text);
    }

    @Override
    public String toString() {
        return "Title:" + " " + name + "/n" + "Text:" + " " + text + "/n";
    }
}
