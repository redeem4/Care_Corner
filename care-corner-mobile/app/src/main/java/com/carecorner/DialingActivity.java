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
    public Chronometer elapsedTimeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialing_activity);
        initViews();
        elapsedTimeCounter.start();
        setCallerInfo(savedInstanceState);

        btnRejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                AlertDialog recordingAlert = builder.create();
                recordingAlert.show();
            }
        });

        btnRejectCall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO: Implement Panic Button Feature and place function call here.
                Toast.makeText(DialingActivity.this, "Panic Button Activated!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initViews() {
        caller_id_text = findViewById(R.id.caller_id_text_2);
        phone_number_text = findViewById(R.id.phone_number_text_2);
        btnRejectCall = findViewById(R.id.btnRejectCall2);
        elapsedTimeCounter = findViewById(R.id.elapsedTime);
    }

    //Function to setup Call Screen based upon User Information
    private void setCallerInfo(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                getNameValue= null;
                getPhoneValue= null;
            } else {
                getNameValue = extras.getString("callerName");
                getPhoneValue = extras.getString("callerPhoneNum");
                caller_id_text.setText(getNameValue);
                phone_number_text.setText(getPhoneValue);
            }
        } else {
            getNameValue = (String) savedInstanceState.getSerializable("callerName");
            getPhoneValue = (String) savedInstanceState.getSerializable("callerPhoneNum");
        }
    }
}