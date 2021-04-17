package com.carecorner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class MombotActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText textEntry, textReply;
    String text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mombot_activity);
        btnSubmit = findViewById(R.id.btnSubmit);
        textEntry = findViewById(R.id.MombotEntryBox);
        textReply = findViewById(R.id.mombotResponseBox);

        //Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = textEntry.getText().toString();
                textReply.setText(response(text));
            }
        });
    }
    public String response(String entry)
    {
        entry = entry.toLowerCase();
        String reply = "";
        if(entry.isEmpty())
            reply = "It seems nothing was entered.";
        else if(entry.contains(" bar"))
            reply = "At a bar you should: watch your drink being poured, " +
                    "not leave your drink unattended, " +
                    "and be aware of your surroundings. ";
        else if(entry.contains(" house"))
            reply = "At a house you should: let a friend know where you are going and who you are seeing " +
                    " and keep your phone with you at all times.";
        else if(entry.contains(" club"))
            reply = "At a club you should: watch your drink being poured, " +
                    "not leave your drink unattended, " +
                    "and be aware of your surroundings.";
        else if(entry.contains(" restaurant"))
            reply = "At a restaurant you should: meet at a public place, " +
                    "not leave your drink unattended, " +
                    "and tell someone you trust about your plans.";
        else if(entry.contains(" movies"))
            reply = "At the movies you should: meet at a public place " +
                    "and tell someone you trust about your plans";
        else
            reply = "Sorry, I do not yet have a response for that.";
        return reply;
    }
    /**
     * Overrides the Back Button functionality to return to the Main Menu screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(MombotActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}