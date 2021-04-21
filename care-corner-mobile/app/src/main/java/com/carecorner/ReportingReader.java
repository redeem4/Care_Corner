package com.carecorner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportingReader extends AppCompatActivity {

    TextView title2;
    String reportingName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporting_reader_activity);
        title2 = findViewById(R.id.title2);


        try {
            reportingName = getIntent().getStringExtra("reportingName");
        }
        catch(NullPointerException e) {
            reportingName = " ";
        }
        title2.setText(reportingName);
        String outText = " ";
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(ReportingReader.this, ReportingActivity.class);
        startActivity(intent);
    }
}
