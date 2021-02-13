package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Fake_Phone_Call_Menu_Activity extends AppCompatActivity {

    private Button btnCall, btnRecordings, btnSettings;
    private EditText nameEntryBox, phoneEntryBox, dateEntryBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_phone_call_menu_activity);

        initViews();

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fake_Phone_Call_Menu_Activity.this, Fake_Phone_Call_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnCall = findViewById(R.id.btnCall);
        btnRecordings = findViewById(R.id.btnRecordings);
        btnSettings = findViewById(R.id.btnSettings);
        nameEntryBox = findViewById(R.id.nameEntryBox);
        phoneEntryBox = findViewById(R.id.phoneEntryBox);
        dateEntryBox = findViewById(R.id.dateEntryBox);
    }
}