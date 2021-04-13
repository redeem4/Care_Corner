package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class JournalMenuActivity extends AppCompatActivity {

    private Button btnHome, btnView;

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

        //button to view list and open journal
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JournalMenuActivity.this, JournalRecyclerMain.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Connects and initializes every element in the layout to a variable.
     */
    private void initViews() {
        btnHome = findViewById(R.id.btnHome);
        btnView = findViewById(R.id.btnView);
    }

    /**
     * Overrides the Back Button functionality to return to the Journal Main Menu.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(JournalMenuActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}









