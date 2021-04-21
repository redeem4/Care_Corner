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