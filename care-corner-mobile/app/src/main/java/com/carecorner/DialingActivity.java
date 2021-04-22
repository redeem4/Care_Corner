package com.carecorner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.carecorner.RecorderService.RecorderBinder;

import java.io.File;

public class DialingActivity extends AppCompatActivity {

    private TextView caller_id_text, phone_number_text;
    private ImageButton btnRejectCall;
    private String getNameValue, getPhoneValue;
    private int fake_call_voice_selection;
    public Chronometer elapsedTimeCounter;

    //Media Recorder Variables
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;
    private String recordFile;
    private RecorderService recorderService;
    //is this service bound to a client?
    private boolean isBound = false;
    private boolean isRecording = false;
    private TextView timer;
    private MediaRecorder mediaRecorder;
    private String lastRecording;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialing_activity);
        initViews();
        //Rec_requirement
        Intent recorderIntent = new Intent(this, RecorderService.class);
        bindService(recorderIntent, recorderConnection, Context.BIND_AUTO_CREATE);

        elapsedTimeCounter.start();
        setCallerInfo(savedInstanceState);
        start_fake_call_voice(fake_call_voice_selection);





        btnRejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_fake_call_voice();

                lastRecording = recorderService.getLastRecording();
                stop_audio_recording();
                showDialog();
            }
        });

        btnRejectCall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO: Implement Panic Button Feature and place function call here.
                stop_fake_call_voice();
                lastRecording = recorderService.getLastRecording();
                stop_audio_recording();
                Toast.makeText(DialingActivity.this, "Panic Button Activated!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    /**
     * Sets up Dialing Screen based upon User Input initially gathered from the Fake Phone Call Menu Activity
     * @param savedInstanceState This variable carries the saved User Input from the previous Activity.
     */
    //Function to setup Call Screen based upon User Information
    private void setCallerInfo(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                getNameValue= null;
                getPhoneValue= null;
                fake_call_voice_selection = 0;
            } else {
                getNameValue = extras.getString("callerName");
                getPhoneValue = extras.getString("callerPhoneNum");
                fake_call_voice_selection = extras.getInt("callerVoice");
                caller_id_text.setText(getNameValue);
                phone_number_text.setText(getPhoneValue);
            }
        } else {
            getNameValue = (String) savedInstanceState.getSerializable("callerName");
            getPhoneValue = (String) savedInstanceState.getSerializable("callerPhoneNum");
            fake_call_voice_selection = (int) savedInstanceState.getSerializable("callerVoice");
        }
    }

    /**
     * Connects and initializes every element in the layout to a variable.
     */
    private void initViews() {
        caller_id_text = findViewById(R.id.caller_id_text_2);
        phone_number_text = findViewById(R.id.phone_number_text_2);
        btnRejectCall = findViewById(R.id.btnEndCall);
        elapsedTimeCounter = findViewById(R.id.elapsedTime);
    }

    /**
     * Overrides the Back Button functionality to return to the Fake Phone Call Menu.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(DialingActivity.this, FakePhoneCallMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Creates and Displays Dialog box which asks the user if they would like to save the recording.
     */
    public AlertDialog showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialingActivity.this);
        builder.setTitle("Recording:")
            .setMessage("Would you like to save this recording?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO: Create working save recordings algorithm and place here.
                    Intent intent = new Intent(DialingActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO: Implement delete audio/video recording function and place here.
                    //deleteLast();
                    File fileToBeDeleted = new File(lastRecording);
                    boolean deleted = fileToBeDeleted.delete();
                    Intent intent = new Intent(DialingActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }


    //Rec_requirement
    private void start_audio_recording() {
        if (checkPermissions()) {
            //TODO create recorder Intent in onCreate

            recorderService.startRecording(" ");

            isRecording = true;
        }
    }
    //Rec_requirement
    private void stop_audio_recording() {
        recorderService.stopRecording();
        //stopRecording();


        isRecording = false;
    }

    /**
     *  this function is called to start the fake call voice
     *  @param fake_voice_selection     this maps to the different voice recording
     *                                  0 = Male
     *                                  1 = Female
     */
    public void start_fake_call_voice(int fake_voice_selection){

        Intent intent = new Intent(this, EmulatedVoiceService.class);

        //passing the fake_voice_call as a parameter to the EmulatedVoiceService
        intent.putExtra("callerVoice", fake_voice_selection);
        startService(intent);
    }

    /**
     * this function is called when the fake voice needs to be stopped.
     */
    public void stop_fake_call_voice(){
        Intent intent = new Intent(this, EmulatedVoiceService.class);
        stopService(intent);
    }

    //Rec_requirement
    private ServiceConnection recorderConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RecorderBinder binder = (RecorderBinder) service;
            recorderService =  binder.getService();
            start_audio_recording();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    //TODO move permission code to fake Phone Screen
    private boolean checkPermissions() {
        if(ActivityCompat.checkSelfPermission(this, recordPermission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            ActivityCompat.requestPermissions(this, new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }

    }
}