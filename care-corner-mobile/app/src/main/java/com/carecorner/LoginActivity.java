package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.carecorner.util.NetworkConnection;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister, BtnForgotUsername, BtnForgotPassword;
    EditText usernameEntryBox, passwordEntryBox;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();
        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean networkActive = NetworkConnection.
                        hasActiveInternetConnection(getApplicationContext());

                if (networkActive) {
                    AndroidNetworking.get("http://localhost:4566/api/users")
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                }

                                @Override
                                public void onError(ANError anError) {
                                }
                            });
                } else {
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
            }

        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        BtnForgotUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotUsernameActivity.class);
                startActivity(intent);
            }
        });
        BtnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initViews() {
        btnLogin = findViewById(R.id.btnLogin);
        usernameEntryBox = findViewById(R.id.usernameEntryBox);
        passwordEntryBox = findViewById(R.id.passwordEntryBox);
        btnRegister = findViewById(R.id.btnRegister);
        BtnForgotUsername = findViewById(R.id.btnForgotUsername);
        BtnForgotPassword = findViewById(R.id.btnForgotPassword);
    }

    /**
     * Overrides the Back Button functionality to return to the welcome screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}