package com.carecorner;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Vector;

public class IncidentListService extends Service {

    private final IBinder incidentListBinder = new IncidentListBinder();
    private Vector<Incident> incidents_list;
    public IncidentListService() {
    }

    @Override
    //client calls to bound service start here
    public IBinder onBind(Intent intent) {return incidentListBinder;}




    public void saveIncident(Context ctx, Vector<Incident> list) {
        incidents_list = (Vector<Incident>)list.clone();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(incidents_list);
        editor.putString(this.getString(R.string.incident_list_key), json);
        editor.apply();
    }

    public Vector<Incident> loadIncidents(Context ctx){
        //Context ctx = getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ctx.getString(R.string.incident_list_key), null);
        Type type = new TypeToken<Vector<Incident>>() {}.getType();
        incidents_list = gson.fromJson(json, type);

        if (incidents_list == null) {
            incidents_list = new Vector<Incident>();
        }
        return incidents_list;
    }

    //always needed to bind client to service
    public class IncidentListBinder extends Binder {
        IncidentListService getService(){
            return IncidentListService.this;
        }
    }
}
