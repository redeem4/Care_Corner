package com.carecorner;

public class Journal {
    private String name;
    private String text;

    /**
     * Default Constructor for the Journal Class
     * @param  name  The name of the Journal
     * @param  text The text of the Journal
     */
    public Journal(String name, String text)
    {
        this.name = name;
        this.text = text;
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
}
