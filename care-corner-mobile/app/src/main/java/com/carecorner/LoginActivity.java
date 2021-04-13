package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import okhttp3.Response;
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
                String username = usernameEntryBox.getText().toString();
                String password = passwordEntryBox.getText().toString();

                if (username.equals("demo") && password.equals("demo")) {
                    startActivity(intent);
                } else {
                    apiAuthenticate(intent, username, password);
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

    private void apiAuthenticate(Intent intent, String username, String password) {
        CareCornerApplication application = (CareCornerApplication)getApplicationContext();
        String authUrl = application.api + "/api/auth";
        JSONObject credentials = new JSONObject();
        try {
            credentials.put("username", username);
            credentials.put("password", password);
        } catch(Exception error) {
            Log.e("Login:", "Issue creating credentaion Json");
        }

        AndroidNetworking.post(authUrl)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(credentials)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        if (response.isSuccessful()) {
                            // successful login
                            startActivity(intent);
                        } else
                            // unsuccessful login
                            Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                        counter--;

                        if (counter == 0) {
                            btnLogin.setEnabled(false);
                            btnLogin.setBackgroundColor(Color.GRAY);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.getResponse().toString());
                    }
                });
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