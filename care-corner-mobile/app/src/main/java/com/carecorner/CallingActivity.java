package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CallingActivity extends AppCompatActivity {

    private TextView caller_id_text, phone_number_text;
    private ImageButton btnAcceptCall, btnRejectCall;
    private String getNameValue, getPhoneValue;
    private int fake_call_voice_selection;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calling_activity);

        initViews();
        setCallerInfo(savedInstanceState);
        ringToneStart();

        btnAcceptCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add Start Audio/Video Recording Functions
                ringToneStop();
                Intent intent = new Intent(CallingActivity.this, DialingActivity.class);
                intent.putExtra("callerName", getNameValue);
                intent.putExtra("callerPhoneNum", getPhoneValue);
                intent.putExtra("callerVoice", fake_call_voice_selection);
                startActivity(intent);
            }
        });

        btnRejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringToneStop();
                Intent intent = new Intent(CallingActivity.this, FakePhoneCallMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Sets up Call Screen based upon User Input gathered from the Fake Phone Call Menu Activity
     * @param savedInstanceState This variable carries the saved User Input from the previous Activity.
     */
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
        caller_id_text = findViewById(R.id.caller_id_text);
        phone_number_text = findViewById(R.id.phone_number_text);
        btnAcceptCall = findViewById(R.id.btnAcceptCall);
        btnRejectCall = findViewById(R.id.btnRejectCall);
        player  = MediaPlayer.create(CallingActivity.this, R.raw.default_ringtone);
    }

    /**
     * Overrides the Back Button functionality to stop music player
     * and return to the Fake Phone Call Menu.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        player.stop();
        Intent intent = new Intent(CallingActivity.this, FakePhoneCallMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the ringtone for the Calling Activity.
     */
    private void ringToneStart()
    {
        if(player!=null)
        {
            player.start();
            player.setLooping(true);
        }
    }

    /**
     * Stops the ringtone for the Calling Activity.
     */
    private void ringToneStop()
    {
        if(player != null)
        {
            player.stop();
            player.release();
        }
    }
}