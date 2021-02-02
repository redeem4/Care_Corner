package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText usernameEntryBox, passwordEntryBox;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        btnLogin = findViewById(R.id.btnLogin);
        usernameEntryBox = findViewById(R.id.usernameEntryBox);
        passwordEntryBox = findViewById(R.id.passwordEntryBox);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Create proper and secure login functionality that checks login information against database entries.
                //Currently, no password is needed for debugging reasons.
                if(usernameEntryBox.getText().toString().equals("") &&
                        passwordEntryBox.getText().toString().equals("")) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    counter--;

                    if (counter == 0) {
                        btnLogin.setEnabled(false);
                        btnLogin.setBackgroundColor(Color.GRAY);
                    }
                }
            }
        });
    }
}