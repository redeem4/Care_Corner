package com.carecorner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FakePhoneCallMenuActivity extends AppCompatActivity {

    private Button btnCall, btnRecordings, btnSettings, btnSet;
    private EditText nameEntryBox, phoneEntryBox;
    private String name, phone, voice;
    private Spinner waitTime, voiceSelector;
    private int timeToStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_phone_call_menu_activity);

        initViews();
        spinnerSetup();
        saveSetupData();

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        Intent intent = new Intent(FakePhoneCallMenuActivity.this, CallingActivity.class);
                        intent.putExtra("callerName", nameEntryBox.getText().toString());
                        intent.putExtra("callerPhoneNum", phoneEntryBox.getText().toString());
                        intent.putExtra("callerVoice", voice);
                        startActivity(intent);
                    }
                }, timeToStart);
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEntryBox.getText().toString();
                phone = phoneEntryBox.getText().toString();

                //Below code segment saves fake phone call setup data
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(FakePhoneCallMenuActivity.this);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("name", name);
                editor.putString("phone", phone);
                editor.apply();

                Toast.makeText(FakePhoneCallMenuActivity.this, "Settings Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spinnerSetup() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerWaitTime, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waitTime.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinnerVoice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        voiceSelector.setAdapter(adapter2);

        waitTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String wait = waitTime.getItemAtPosition(position).toString();
                if(wait.equals("Now"))
                {
                    timeToStart = 0;
                }

                if(wait.equals("15 seconds"))
                {
                    timeToStart = 15000;
                }

                if(wait.equals("1 minute"))
                {
                    timeToStart = 60000;
                }

                if(wait.equals("30 minutes"))
                {
                    timeToStart = 1800000;
                }

                if(wait.equals("1 hour"))
                {
                    timeToStart = 3600000;
                }

                if(wait.equals("3 hours"))
                {
                    timeToStart = 1080000;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Auto-generated method stub
            }
        });

        voiceSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String voiceSelect = voiceSelector.getItemAtPosition(position).toString();
                //TODO: Replace below lines with correct audio file information when available.
                if(voiceSelect.equals("malevoice.mp3"))
                {
                    voice = "raw/malevoice.mp3";
                }

                if(voiceSelect.equals("femalevoice.mp3"))
                {
                    voice = "raw/femalevoice.mp3";
                }

                if(voiceSelect.equals("femalevoice2.mp3"))
                {
                    voice = "raw/femalevoice2.mp3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Auto-generated method stub
            }
        });
    }

    private void saveSetupData() {
        //Below Code Segment retrieves fake phone call setup data from SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name1 = prefs.getString("name", "");
        nameEntryBox.setText("" + name1);

        String phone1 = prefs.getString("phone", "");
        phoneEntryBox.setText("" + phone1);
    }

    private void initViews() {
        btnCall = findViewById(R.id.btnCall);
        btnRecordings = findViewById(R.id.btnRecordings);
        btnSettings = findViewById(R.id.btnSettings);
        btnSet = findViewById(R.id.btnSet);
        nameEntryBox = findViewById(R.id.nameEntryBox);
        phoneEntryBox = findViewById(R.id.phoneEntryBox);
        waitTime = findViewById(R.id.spinnerWaitTime);
        voiceSelector = findViewById(R.id.spinnerVoiceSelector);
    }
}