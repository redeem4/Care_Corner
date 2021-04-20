package com.carecorner;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class IncidentTest {

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void incidentConstructorTest(){
        Incident incident = new Incident();

        /*
        System.out.println("Incident id: " + incident.getId());
        System.out.println("Recording File Name: " + incident.getRecording_file_name());
        System.out.println("Journey File Name: " + incident.getJourney_file_name());
        System.out.println("Start Time: " + incident.getStart_time());
        System.out.println("Stop Time: " + incident.getStop_time());
        System.out.println("**After Stop Time Set**");*/
        System.out.println(incident);
        incident.setStop_time();
        System.out.println("Stop Time: " + incident.getStop_time());
    }
}