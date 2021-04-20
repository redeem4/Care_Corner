package com.carecorner;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.After;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Vector;

import static org.junit.Assert.*;

public class IncidentTest {

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void incidentConstructorTest(){
        Incident incident = new Incident();


        System.out.println(incident);
        incident.setStop_time();
        System.out.println("Stop Time: " + incident.getStop_time());
    }


}