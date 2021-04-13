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
