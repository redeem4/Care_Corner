package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CallingActivity extends AppCompatActivity {

    private TextView caller_id_text, phone_number_text;
    private ImageButton btnAcceptCall, btnRejectCall;
    private String getNameValue, getPhoneValue;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calling_activity);

        player  = MediaPlayer.create(this, R.raw.default_ringtone);
        player.setLooping(false);
        player.start();

        initViews();
        setCallerInfo(savedInstanceState);

        btnAcceptCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add Start Audio/Video Recording Functions
                player.stop();
                Intent intent = new Intent(CallingActivity.this, DialingActivity.class);
                intent.putExtra("callerName", getNameValue);
                intent.putExtra("callerPhoneNum", getPhoneValue);
                startActivity(intent);
            }
        });

        btnRejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                Intent intent = new Intent(CallingActivity.this, FakePhoneCallMenuActivity.class);
                startActivity(intent);
            }
        });
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

    private void initViews() {
        caller_id_text = findViewById(R.id.caller_id_text);
        phone_number_text = findViewById(R.id.phone_number_text);
        btnAcceptCall = findViewById(R.id.btnAcceptCall);
        btnRejectCall = findViewById(R.id.btnRejectCall);
    }
}