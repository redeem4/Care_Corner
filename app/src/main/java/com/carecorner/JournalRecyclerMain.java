package com.carecorner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class JournalRecyclerMain extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, AddJournalEntryDialog.AddJournalEntryDialogListener {

    List<Journal> data;
    MyRecyclerViewAdapter adapter;
    private Button btnDelete, btnEdit, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        initViews();
        setupRecyclerView();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    /**
     * Manages what happens when a Journal Entry is clicked on.
     * @param  view  This variable is needed for the onClickListener to work
     * @param  position The location of the Journal Entry in the RecyclerView
     */
    @Override
    public void onItemClick(View view, int position) {
         Intent intent = new Intent(JournalRecyclerMain.this, JournalEditorActivity.class);
         intent.putExtra("journalName", adapter.getItem(position).getName());
         intent.putExtra("text", adapter.getItem(position).getText());
         startActivity(intent);
    }

    /**
     * This function deletes the journal entry from the RecyclerView.
     * @param  position The location of the Journal Entry in the RecyclerView to be removed
     */
    @Override
    public void onDeleteBtnClick(int position) {
        int removeIndex = position;
        data.remove(removeIndex);
        adapter.notifyItemRemoved(removeIndex);
    }

    /**
     * Sets up the Add Journal Entry Dialog.
     */
    public void openDialog() {
        AddJournalEntryDialog addJournalEntry = new AddJournalEntryDialog();
        addJournalEntry.show(getSupportFragmentManager(), "example dialog");
    }

    /**
     * This function takes the data the user entered from the Add Journal Entry Dialog and adds it to the RecyclerView.
     * @param  journalName  The name of the Journal Entry to be added
     * @param  journalEntry The text of the Journal Entry to be added
     */
    @Override
    public void applyTexts(String journalName, String journalEntry) {
        int insertIndex = data.size();
        Journal temp = new Journal(journalName, journalEntry);
        data.add(insertIndex, temp);
        adapter.notifyItemInserted(insertIndex);
    }

    /**
     * This function sets up the RecyclerView.
     */
    private void setupRecyclerView() {
        // data to populate the RecyclerView with
        data = new ArrayList<>();
        data.add(new Journal("test 3/13/2021", "This is a test 1"));
        data.add(new Journal("test 3/14/2021", "This is a test 2"));
        data.add(new Journal("test 3/15/2021", "This is a test 3"));
        data.add(new Journal("test 3/16/2021", "This is a test 4"));
        data.add(new Journal("test 3/17/2021", "This is a test 5"));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new MyRecyclerViewAdapter(this, data, this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Connects and initializes every element in the layout to a variable.
     */
    private void initViews() {
        btnDelete = findViewById(R.id.btnDelete2);
        btnCreate = findViewById(R.id.btnCreate2);
    }
}