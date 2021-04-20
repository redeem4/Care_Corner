package com.carecorner;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JournalLogin extends AppCompatActivity {
    private Button btnJournalLogin, btnJournalPinCreate;
    private EditText txtJournalPinBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_login);
        initViews();


        btnJournalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JournalLogin.this, JournalMenuActivity.class);
                //TODO: Create proper and secure login functionality that checks login information against database entries
                Editable txtPin = txtJournalPinBox.getText();
                if (txtPin.equals("1111")) {
                    startActivity(intent);
                }
            }
        });

        btnJournalPinCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Create proper and secure login functionality that checks login information against database entries
                Intent intent = new Intent(JournalLogin.this, JournalPinCreation.class);
                startActivity(intent);
            }
        });

    }


    private void initViews() {
        txtJournalPinBox = findViewById(R.id.journalPinBox);
        btnJournalLogin = findViewById(R.id.btnJournalLogin);
        btnJournalPinCreate = findViewById(R.id.btnJournalPinCreate);

    }

    /**
     * Overrides the Back Button functionality to return to the Main menu screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(JournalLogin.this, MainMenuActivity.class);
        startActivity(intent);
    }
}