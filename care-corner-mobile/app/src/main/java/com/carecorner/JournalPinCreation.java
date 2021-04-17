package com.carecorner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JournalPinCreation extends AppCompatActivity {
    private EditText txtJournalUserName, txtJournalPassword, txtJournalPinBox, txtJournalPinConfirm;
    private Button btnPinCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_pin_creation);
        initViews();

        Intent intent = new Intent(JournalPinCreation.this, JournalLogin.class);
    }

    private void initViews() {
        txtJournalUserName = findViewById(R.id.txtJournalUserName);
        txtJournalPassword = findViewById(R.id.txtJournalPassword);
        txtJournalPinBox = findViewById(R.id.txtJournalPinBox);
        txtJournalPinConfirm = findViewById(R.id.txtJournalPinConfirm);
        btnPinCreation = findViewById(R.id.btnPinCreation);
    }

    /**
     * Overrides the Back Button functionality to return to the Journal login page screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(JournalPinCreation.this, JournalLogin.class);
        startActivity(intent);
    }
}
