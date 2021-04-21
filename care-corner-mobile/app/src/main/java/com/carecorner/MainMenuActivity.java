package com.carecorner;


import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Set;

public class MainMenuActivity extends AppCompatActivity {

    private ImageButton btnFakePhoneCall, btnMomBot, btnSafeWalk, btnJournal, btnResourcesMenu, btnReportingAssistance, btnSettings, btnPanicButton;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        initViews();
        checkPermissions();

        btnFakePhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, FakePhoneCallMenuActivity.class);
                startActivity(intent);
            }
        });
        btnMomBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MombotActivity.class);
                startActivity(intent);
            }
        });
        btnSafeWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ArmedWalkStart.class);
                                startActivity(intent);
            }
        });

        btnJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, JournalMenuActivity.class);
                startActivity(intent);
            }
        });

        btnResourcesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ResourceActivity.class);
                startActivity(intent);
            }
        });

        btnSafeWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SafeWalkMenuActivity.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnPanicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnReportingAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ReportingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnFakePhoneCall = findViewById(R.id.btnFakePhoneCall);
        btnMomBot = findViewById(R.id.btnMomBot);
        btnSafeWalk = findViewById(R.id.btnSafeWalk);
        btnJournal = findViewById(R.id.btnJournal);
        btnResourcesMenu = findViewById(R.id.btnResourcesMenu);
        btnReportingAssistance = findViewById(R.id.btnReportingAssistance);
        btnPanicButton = findViewById(R.id.btnPanicButton);
        btnSettings = findViewById(R.id.btnSetting);
    }

    /**
     * Overrides the Back Button functionality to return to the login screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
        startActivity(intent);
    }

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