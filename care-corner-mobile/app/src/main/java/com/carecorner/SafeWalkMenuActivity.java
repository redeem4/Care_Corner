package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.carecorner.api.JourneyApi;

public class SafeWalkMenuActivity extends AppCompatActivity {
    Button btnStartWalk, btnWalking, btnArrived;
    EditText destinationEntryBox, etaEntryBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_walk_menu_activity);
        initViews();

        btnStartWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWalk();
            }
        });

        btnWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueWalk();
            }
        });

        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endWalk();
            }
        });
    }

    private void initViews() {
        btnStartWalk = findViewById(R.id.btnStartWalk);
        btnArrived = findViewById(R.id.btnArrived);
        btnWalking = findViewById(R.id.btnWalking);
        destinationEntryBox = findViewById(R.id.destinationEntryBox);
        etaEntryBox = findViewById(R.id.etaEntryBox);
    }


    private void startWalk() {
        String destination = destinationEntryBox.getText().toString();
        String eta = etaEntryBox.getText().toString();

        CareCornerApplication.getSession().setArmedWalkState(true);
        JourneyApi.bonVoyage(destination, eta, "80.00", "30.00");
    }


    private void continueWalk() {
        JourneyApi.wayPoint("80.00", "30.00");
    }


    private void endWalk() {
        CareCornerApplication.getSession().setArmedWalkState(false);
        JourneyApi.arrived("80.00", "30.00");
    }

    /**
     * Overrides the Back Button functionality to return to the welcome screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(SafeWalkMenuActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}