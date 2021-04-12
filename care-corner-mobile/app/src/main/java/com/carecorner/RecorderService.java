package com.carecorner;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RecorderService extends Service {

    private final IBinder recorderBinder = new RecorderBinder();
    public RecorderService() {
    }

    @Override
    //client calls to bound service start here
    public IBinder onBind(Intent intent) {
        return  recorderBinder;
    }

    public String getCurrentTime(){
        SimpleDateFormat df=  new SimpleDateFormat("HH:mm:ss", Locale.US);
        return (df.format(new Date()));
    }

    //always needed to bind client to service
    public class RecorderBinder extends  Binder{
        RecorderService getService(){
            return RecorderService.this;
        }
    }
}