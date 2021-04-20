package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ResourceActivity extends AppCompatActivity {
    private Button btnShelter, btnEducation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_activity);
        initViews();

    }



    /**
     * Overrides the Back Button functionality to return to the welcome screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(ResourceActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private  void initViews(){
        btnShelter = findViewById(R.id.btnShelter);
        btnEducation = findViewById(R.id.btnEducation);
    }
}
