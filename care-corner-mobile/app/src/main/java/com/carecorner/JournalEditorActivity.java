package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;


public class JournalEditorActivity extends AppCompatActivity {

    //Save button, Exit button
    Button btnSave, btnExit;
    //Editable text entry box
    EditText textEntryBox;
    EditText titleBox;
    //Check to save before exiting, set to true in case user does not edit.
    boolean hasBeenSaved = true;
    //Convert file passed in from JournalActivity to a string
    String text = "";
    String title = "";
    //Bundle extras = getIntent().getExtras();

    //text = (String) savedInstanceState.getSerializable("text"); // extras.getString("text");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Prep
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_editor_activity);
        //Intent intent = new Intent(JournalEditorActivity.this, JournalActivity.class);
        //Links
        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        textEntryBox = findViewById(R.id.textEntryBox);
        titleBox = findViewById(R.id.titleBox);

        try {
            //text = getIntent().getExtras().getString("text");
            text = getIntent().getStringExtra("text");
            title = getIntent().getStringExtra("title");
        }
        catch(NullPointerException e) {
            text = " ";
            title = " ";
        }
        //Puts the editable text into the text entry box
        textEntryBox.setText(text, TextView.BufferType.EDITABLE);
        titleBox.setText(text, TextView.BufferType.EDITABLE);

        //Save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieves the text from the text box
                text = textEntryBox.getText().toString();
                title = titleBox.getText().toString();
                //Confirms to user save has been made
                Toast.makeText(JournalEditorActivity.this, "Entry saved", Toast.LENGTH_SHORT).show();
                //Marks entry saved
                hasBeenSaved = true;
            }
        });

        //Exit button
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if saved
                if(hasBeenSaved) {
                    //Exit back to Journal Activity and return text
                    Toast.makeText(JournalEditorActivity.this,
                            "Exiting", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(JournalEditorActivity.this, JournalRecyclerMain.class);
                    intent.putExtra("text", text);
                    intent.putExtra("title", title);
                    setResult(RESULT_OK, intent);
                    finish();
                    //startActivity(intent);
                }
                else {
                    //Remind user to save, if user wants to exit without saving simply press exit again.
                    Toast.makeText(JournalEditorActivity.this,
                            "Entry has not been saved, to exit without saving press Exit again", Toast.LENGTH_SHORT).show();
                    //Allows user to just press exit again
                    hasBeenSaved = true;
                    }
                }
        });

        textEntryBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Assumes click leads to an edit, not always true, but safe rather than sorry
                hasBeenSaved = false;
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(JournalEditorActivity.this, JournalRecyclerMain.class);
        startActivity(intent);
    }

}