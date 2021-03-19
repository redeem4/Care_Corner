package com.carecorner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DialingActivity extends AppCompatActivity {

    private TextView caller_id_text, phone_number_text;
    private ImageButton btnRejectCall;
    private String getNameValue, getPhoneValue;
    private int getVoiceValue; //stores users fake call voice selection
    public Chronometer elapsedTimeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialing_activity);
        initViews();
        elapsedTimeCounter.start();
        setCallerInfo(savedInstanceState);
        startVoice(getVoiceValue);

        btnRejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVoice();
                showDialog();
            }
        });

        btnRejectCall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO: Implement Panic Button Feature and place function call here.
                stopVoice();
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
                getVoiceValue= 0;
            } else {
                getNameValue = extras.getString("callerName");
                getPhoneValue = extras.getString("callerPhoneNum");
                getVoiceValue = extras.getInt("callerVoice");
                caller_id_text.setText(getNameValue);
                phone_number_text.setText(getPhoneValue);
            }
        } else {
            getNameValue = (String) savedInstanceState.getSerializable("callerName");
            getPhoneValue = (String) savedInstanceState.getSerializable("callerPhoneNum");
            getVoiceValue = (int) savedInstanceState.getSerializable("callerVoice");
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
                    Intent intent = new Intent(DialingActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }
    /*  this function is called when the fake voice is ready to be started
     *  @param voice - this maps to the different voice recording
     *                 0 = Male
     *                 1 = Female
     */
    public void startVoice(int voice){

        Intent intent = new Intent(this, EmulatedVoiceService.class);
        intent.putExtra("callerVoice", voice);

        startService(intent);
        String test = "intent was" + voice;
    }

    /* this function is called when the fake voice needs to be stopped.
     */
    public void stopVoice(){
        Intent intent = new Intent(this, EmulatedVoiceService.class);
        stopService(intent);
    }
}