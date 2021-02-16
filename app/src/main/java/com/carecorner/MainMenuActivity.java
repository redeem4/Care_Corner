package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {

    private ImageButton btnFakePhoneCall, btnMomBot, btnSafeWalk, btnJournal, btnResourcesMenu, btnReportingAssistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        initViews();

        btnFakePhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, FakePhoneCallMenuActivity.class);
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
    }
}