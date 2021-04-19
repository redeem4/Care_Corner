package com.carecorner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Journal {
    private String name;
    private String text;
    private String date;
    private String time;

    private SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat s2 = new SimpleDateFormat("hh:mm");

    /**
     * Default Constructor for the Journal Class
     * @param  name  The name of the Journal
     * @param  text The text of the Journal
     */
    public Journal(String name, String text)
    {
        this.name = name;
        this.text = text;
        this.date = s.format(new Date());
        this.time = s2.format(new Date());
    }

    /**
     * Default Constructor for the Journal Class
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
