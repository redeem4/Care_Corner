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
        entry = " " + entry; //Checks for the silly case of someone only entering "Bar"
        String reply = "";
        if(entry.isEmpty())
            reply = "It seems nothing was entered.";
        else if(entry.contains(" bar"))
            reply = "At a bar you should: \n watch your drink being poured, " +
                    "\n not leave your drink unattended, " +
                    "\n and be aware of your surroundings. " +
                    "\n Consider using Safe Walk on your journey there or setting up a Fake Call";
        else if(entry.contains(" house"))
            reply = "At a house you should: \n let a friend know where you are going and who you are seeing " +
                    "\n and keep your phone with you at all times."+
                    "\n Consider using Safe Walk on your journey there or setting up a Fake Call";
        else if(entry.contains(" club"))
            reply = "At a club you should: \n watch your drink being poured, " +
                    "\n not leave your drink unattended, " +
                    "\n and be aware of your surroundings."+
                    "\n Consider using Safe Walk on your journey there or setting up a Fake Call";
        else if(entry.contains(" restaurant"))
            reply = "At a restaurant you should: \n meet at a public place, " +
                    "\n not leave your drink unattended, " +
                    "\n and tell someone you trust about your plans."+
                    "\n Consider using Safe Walk on your journey there or setting up a Fake Call";
        else if(entry.contains(" movies"))
            reply = "At the movies you should: \n meet at a public place " +
                    "\n and tell someone you trust about your plans"+
                    "\n Consider using Safe Walk on your journey there or setting up a Fake Call";
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