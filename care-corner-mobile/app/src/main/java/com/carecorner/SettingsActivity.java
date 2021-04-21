package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    //Button
    Button btnSave;

    //Emergency Contact (Name/Phone) (#)
    EditText eCN1, eCN2, eCN3, eCP1, eCP2, eCP3;

    String nm1, nm2, nm3, ph1, ph2, ph3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnSave = findViewById(R.id.btnSaveContacts);
        eCN1 = findViewById(R.id.EmergencyContactName1);
        eCN2 = findViewById(R.id.EmergencyContactName2);
        eCN3 = findViewById(R.id.EmergencyContactName3);
        eCP1 = findViewById(R.id.EmergencyContactNumber1);
        eCP2 = findViewById(R.id.EmergencyContactNumber2);
        eCP3 = findViewById(R.id.EmergencyContactNumber3);

        //Gustin FancyStuff
        //getFromDatabase(n1, n2, n3, p1, p2, p3);
        //nm1 = getFD(n1).toString(); etc?
        nm1 = " ";
        nm2 = " ";
        nm3 = " ";
        ph1 = " ";
        ph2 = " ";
        ph3 = " ";

        eCN1.setText(nm1, TextView.BufferType.EDITABLE);
        eCN2.setText(nm2, TextView.BufferType.EDITABLE);
        eCN3.setText(nm3, TextView.BufferType.EDITABLE);
        eCP1.setText(ph1, TextView.BufferType.EDITABLE);
        eCP2.setText(ph2, TextView.BufferType.EDITABLE);
        eCP3.setText(ph3, TextView.BufferType.EDITABLE);


        //Create button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieves the text from the text box
                nm1 = eCN1.getText().toString();
                nm2 = eCN2.getText().toString();
                nm3 = eCN3.getText().toString();
                ph1 = eCP1.getText().toString();
                ph2 = eCP2.getText().toString();
                ph3 = eCP3.getText().toString();

                //GUSTIN fancy stuff
                //sendToDatabase(username, password, email, nm1, nm2, nm3, ph1, ph2, ph3)

                Intent intent = new Intent(SettingsActivity.this, MainMenuActivity.class);
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
        Intent intent = new Intent(SettingsActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}