package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JournalReader extends AppCompatActivity {

    //edit button
    Button btnEdit;
    //text reader box
    TextView textBox;
    //Convert file passed in from JournalActivity to a string
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_reader_activity);
        textBox = findViewById(R.id.textBox);
        btnEdit = findViewById(R.id.btnEdit);

        try {
            text = getIntent().getStringExtra("text");
        }
        catch(NullPointerException e) {
            text = " ";
        }

        textBox.setText(text);

        String outText = " ";

        //Edit Button
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text= textBox.getText().toString();
//                Toast.makeText(JournalReader.this, "Sending to editor", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JournalReader.this, JournalEditorActivity.class);
                intent.putExtra("text", text);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

