package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FakePhoneCallMenuActivity extends AppCompatActivity {

    private Button btnCall, btnRecordings, btnSettings, btnSet;
    private EditText nameEntryBox, phoneEntryBox;
    private String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_phone_call_menu_activity);

        initViews();

        //Below Code Segment retrieves fake phone call setup data from SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name1 = prefs.getString("name", "");
        nameEntryBox.setText("" + name1);

        String phone1 = prefs.getString("phone", "");
        phoneEntryBox.setText("" + phone1);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FakePhoneCallMenuActivity.this, CallingActivity.class);
                intent.putExtra("callerName", nameEntryBox.getText().toString());
                intent.putExtra("callerPhoneNum", phoneEntryBox.getText().toString());
                startActivity(intent);
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEntryBox.getText().toString();
                phone = phoneEntryBox.getText().toString();

                //Below code segment saves fake phone call setup data
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(FakePhoneCallMenuActivity.this);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("name", name);
                editor.putString("phone", phone);
                editor.apply();

                Toast.makeText(FakePhoneCallMenuActivity.this, "Settings Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        btnCall = findViewById(R.id.btnCall);
        btnRecordings = findViewById(R.id.btnRecordings);
        btnSettings = findViewById(R.id.btnSettings);
        btnSet = findViewById(R.id.btnSet);
        nameEntryBox = findViewById(R.id.nameEntryBox);
        phoneEntryBox = findViewById(R.id.phoneEntryBox);
    }
}