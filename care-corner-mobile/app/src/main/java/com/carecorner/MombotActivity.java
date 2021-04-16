package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MombotActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText textEntry, textReply;
    String text = "";
    btnSubmit = findViewById(R.id.btnSubmit);
    textEntry = findViewById(R.id.MombotEntryBox);
    textReply = findViewById(R.id.mombotResponseBox);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mombot);
    }

    /**
     * Overrides the Back Button functionality to return to the Main Menu screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(MombotActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}