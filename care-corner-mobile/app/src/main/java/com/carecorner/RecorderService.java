package com.carecorner;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.os.Binder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RecorderService extends Service {

    private final IBinder recorderBinder = new RecorderBinder();
    private MediaRecorder mediaRecorder;
    private String recordFile;
    private String lastRecording;

    public RecorderService() {
    }

    @Override
    //client calls to bound service start here
    public IBinder onBind(Intent intent) {

        try {
            recordFile = intent.getStringExtra("recordFile");
        }
        catch(NullPointerException e) {
            recordFile = " ";
        }
        return  recorderBinder;
    }

    public String getCurrentTime(){
        SimpleDateFormat df=  new SimpleDateFormat("HH:mm:ss", Locale.US);
        return (df.format(new Date()));
    }

    public void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public void startRecording(String fileName) {

        String recordPath = this.getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now = new Date();

        if(fileName == " "){
            recordFile = "Recording_" + formatter.format(now) + ".3gp";
        }else{
            recordFile = "Recording_" + fileName + ".3gp";}

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        lastRecording = recordPath + "/" + recordFile;

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();

    }


    //always needed to bind client to service
    public class RecorderBinder extends  Binder{
        RecorderService getService(){
            return RecorderService.this;
        }
    }

    public String getLastRecording(){
        return lastRecording;
    }

}