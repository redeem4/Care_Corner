package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

public class JournalMenuActivity extends AppCompatActivity {

    private Button btnHome, btnCreate, btnDelete, btnEdit, btnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_menu_activity);

        initViews();

        //button to click to go back to the home screen
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JournalMenuActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
        //button to view list
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JournalMenuActivity.this, JournalRecyclerMain.class);
                startActivity(intent);
            }
        });
        //button to click to create a new entry
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent intent = new Intent(JournalMenuActivity.this, JournalEditorActivity.class);
             //   startActivity(intent);
            }
        });

        //button to click to delete a selected entry
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //TODO: delete selected journal entry
                //TODO: update screen to remove journal from checkbox
               // Toast.makeText(JournalMenuActivity.this, "Your entry has been deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        //button to click to edit a selected entry
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Intent intent = new Intent(JournalMenuActivity.this, JournalEditorActivity.class);
                //   startActivity(intent);
            }
        });

    }
    private void initViews() {
        btnHome = findViewById(R.id.btnHome);
        btnCreate = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnView = findViewById(R.id.btnView);
    }
}









