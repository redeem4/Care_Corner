package com.carecorner;

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
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class JournalMenuActivity extends AppCompatActivity {

    private Button btnHome, btnCreate, btnDelete, btnRead, btnEdit;
    private CheckBox journalEntries;
    private String myJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_menu_activity);
        // Intent intent = new Intent(JournalMenuActivity.this, MainMenuActivity.class);

        //    initViews();
        //  checkBoxSetup();
        //  saveSetupData();

        //Button to click to go back to Home Screen
        //    btnHome.setOnClickListener(new View.OnClickListener() {
        //        @Override
        //        public void onClick(View v) {
        //           Intent intent = new Intent(JournalMenuActivity.this, MainMenuActivity.class);
        //           startActivity(intent);
        //       }
        //   });

        //Button to click to Create a new entry
        //     btnCreate.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //         public void onClick(View v){
        //             Intent intent = new Intent(JournalMenuActivity.this, JournalEditor.class);
        //            startActivity(intent);
        //        }
        //    });

        //Button to click to delete selected journal entry
        //    btnDelete.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //         public void onClick(View v){
        ////TODO: only delete selected journal entry
        //TODO: update screen to remove journal from checkBox

        //             Toast.makeText(JournalMenuActivity.this, "Your entry has been deleted!", Toast.LENGTH_SHORT).show();
        //         }
        //      });


        //Button to click to open and read selected journal entry
        //     btnRead.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //        public void onClick(View v){
        //             Intent intent = new Intent(JournalMenuActivity.this, JournalEditor.class);
        //             startActivity(intent);
        //TODO: only send selected journal entry
        //        }
        //    });

        //Button to click to edit selected journal entry
        //      btnEdit.setOnClickListener(new View.OnClickListener() {
        ///          @Override
        //          public void onClick(View v){
        //              Intent intent = new Intent(JournalMenuActivity.this, JournalEditor.class);
        //              startActivity(intent);
        //TODO: only send selected journal entry
        //          }
        //     });
    }



//private void checkBoxSetup()
//{

//}








    //   private void initViews() {
    //       btnHome = findViewById(R.id.btnHome);
    //   btnCreate = findViewById(R.id.btnCreate);
    //  btnDelete = findViewById(R.id.btnDelete);
    //  btnRead = findViewById(R.id.btnRead);
    //  btnEdit = findViewById(R.id.btnEdit);
    //  }
}
