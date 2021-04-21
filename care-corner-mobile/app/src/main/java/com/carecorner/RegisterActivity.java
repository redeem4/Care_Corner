package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //Button
    Button btnCreate;

    //Emergency Contact (Name/Phone) (#)
    EditText usernameBox, passwordBox, emailBox, eCN1, eCN2, eCN3, eCP1, eCP2, eCP3;

    String username, password, email, nm1, nm2, nm3, ph1, ph2, ph3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        btnCreate = findViewById(R.id.btnCreateAccount);
        usernameBox = findViewById(R.id.usernameEntryBox);
        passwordBox = findViewById(R.id.passwordEntryBox);
        emailBox = findViewById(R.id.emailEntryBox);
        eCN1 = findViewById(R.id.EmergencyContactName1);
        eCN2 = findViewById(R.id.EmergencyContactName2);
        eCN3 = findViewById(R.id.EmergencyContactName3);
        eCP1 = findViewById(R.id.EmergencyContactNumber1);
        eCP2 = findViewById(R.id.EmergencyContactNumber2);
        eCP3 = findViewById(R.id.EmergencyContactNumber3);

        //Create button
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieves the text from the text box
                username = usernameBox.getText().toString();
                password = passwordBox.getText().toString();
                email = emailBox.getText().toString();
                nm1 = eCN1.getText().toString();
                nm2 = eCN2.getText().toString();
                nm3 = eCN3.getText().toString();
                ph1 = eCP1.getText().toString();
                ph2 = eCP2.getText().toString();
                ph3 = eCP3.getText().toString();

                //GUSTIN fancy stuff
                //sendToDatabase(username, password, email, nm1, nm2, nm3, ph1, ph2, ph3)

                Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Overrides the Back Button functionality to return to the login screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}