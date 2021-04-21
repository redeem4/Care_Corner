package com.carecorner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Incident {
    private String id;
    private String recording_file_name;
    private String journey_file_name;
    private Date start_time;
    private Date stop_time;

    Incident(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now = new Date();

        id = "Incident_" + formatter.format(now);
        recording_file_name = "Recording_" + formatter.format(now) + ".3gp";
        journey_file_name = "Journey_" + formatter.format(now);
        start_time = now;
        stop_time = null;
    }

    public String getId() {
        return id;
    }

    public String getRecording_file_name() {
        return recording_file_name;
    }

    public String getJourney_file_name() {
        return journey_file_name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getStop_time() {
        return stop_time;
    }

    public void setStop_time() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now = new Date();
        this.stop_time = now;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(
                "Incident id: " + this.id + "\n" +
                "Recording File Name: " + this.recording_file_name + "\n" +
                "Journey File Name: " + this.journey_file_name + "\n" +
                "Start Time: " + this.start_time + "\n" +
                "Stop Time: " + this.stop_time + "\n"
        );

        String incident_report = new String(str);
        return incident_report;
    }

}
