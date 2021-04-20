package com.carecorner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JournalRecyclerMain extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, AddJournalEntryDialog.AddJournalEntryDialogListener {

    ArrayList<Journal> data;
    MyRecyclerViewAdapter adapter;
    private Button btnDelete, btnCreate;

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
        saveArrayList(data, "journals");
        Intent intent = new Intent(JournalRecyclerMain.this, JournalReader.class);
        intent.putExtra("journalName", adapter.getItem(position).getUneditedName());
        intent.putExtra("text", adapter.getItem(position).getText());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * This function deletes the journal entry from the RecyclerView.
     * @param  position The location of the Journal Entry in the RecyclerView to be removed
     */
    @Override
    public void onDeleteBtnClick(int position) {
        data.remove(position);
        adapter.notifyItemRemoved(position);
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
        String uneditedJournalName = journalName;

        if(journalName.equals(""))
        {
            journalName = "New Journal Entry";
            uneditedJournalName = journalName;
        }

        if(journalEntry.equals(""))
        {
            journalEntry = "";
        }

        int maxLength = 14;
        if(journalName.length() >=  maxLength)
        {
           journalName = journalName.substring(0, maxLength - 1) + "...";
        }

        Journal temp = new Journal(journalName, journalEntry);
        temp.setUneditedName(uneditedJournalName);
        data.add(insertIndex, temp);
        adapter.notifyItemInserted(insertIndex);
    }

    /**
     * This function sets up the RecyclerView.
     */
    private void setupRecyclerView() {
        data = loadArrayList("journals");

        if(data == null || data.isEmpty())
        {
            // Test data to populate the RecyclerView with if the saved Journal List is empty.
            // Mainly for demonstration purposes.
            data = new ArrayList<Journal>();
            data.add(new Journal("test 3/13/2021", "This is a test 1"));
            data.add(new Journal("test 3/14/2021", "This is a test 2"));
            data.add(new Journal("test 3/15/2021", "This is a test 3"));
            data.add(new Journal("test 3/16/2021", "This is a test 4"));
            data.add(new Journal("test 3/17/2021", "This is a test 5"));
        }
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
        applyEdits();
    }

    /**
     * This function handles all the logic for receiving data from intents and
     * adding/updating the Journal Recycler View and the Journal ArrayList.
     */
    private void applyEdits() {
        String journalTitle = getIntent().getStringExtra("title");
        String journalText = getIntent().getStringExtra("text");
        int positionOfOriginalEntry = getIntent().getIntExtra("position", -1);

        if(journalTitle != "" && journalText != ""
                && journalTitle != null && journalText != null)
        {
            if(!findJournalText(data, journalText)
                    && !findJournalTitle(data, journalTitle))
            {
                Journal temp = new Journal(journalTitle, journalText);
                data.add(temp);
                int insertIndex = data.size();
                adapter.notifyItemInserted(insertIndex);
                data.remove(positionOfOriginalEntry);
                adapter.notifyItemRemoved(positionOfOriginalEntry);
            }

            if(findJournalText(data, journalText)
                    && !findJournalTitle(data, journalTitle))
            {
                int index = getIndexOfJournalText(data, journalText);
                data.get(index).setName(journalTitle);
                adapter.notifyDataSetChanged();
            }

            if(!findJournalText(data, journalText)
                    && findJournalTitle(data, journalTitle))
            {
                int index = getIndexOfJournalTitle(data, journalTitle);
                data.get(index).setText(journalText);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Connects and initializes every element in the layout to a variable.
     */
    private void initViews() {
        btnDelete = findViewById(R.id.btnDelete2);
        btnCreate = findViewById(R.id.btnCreate2);
    }

    /**
     * Overrides the Back Button functionality to return to the Journal Main Menu.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        saveArrayList(data, "journals");
        Intent intent = new Intent(JournalRecyclerMain.this, JournalMenuActivity.class);
        startActivity(intent);
    }

    /**
     * This function converts the Arraylist of Journal Entries into a JSON file, so that it can be saved for later use.
     * @param  data  The ArrayList of Journal Objects
     * @param  key The unique key identifier that will be used to retrieve this specific set of Journal Entries later.
     */
    public void saveArrayList(ArrayList<Journal> data, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(key, json);
        editor.apply();
    }

    /**
     * This function retrieves a saved ArrayList of Journal Objects.
     * @param  key The unique key identifier that will be used to retrieve this specific set of Journal Entries later.
     * @return ArrayList of Journal Objects
     */
    public ArrayList<Journal> loadArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Journal>>() {}.getType();
        return gson.fromJson(json, type);
    }

    /**
     * This function checks to see if the Journal Title exists in the Journal list.
     * @param  data The arrayList of Journal Objects
     * @param title the title that is being searched for
     */
    boolean findJournalTitle(ArrayList<Journal> data, String title)
    {
        for(Journal j : data)
        {
            if (j.getName().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function checks to see if the Journal Text exists in the Journal list.
     * @param  data The arrayList of Journal Objects
     * @param text the text that is being searched for
     */
    boolean findJournalText(ArrayList<Journal> data, String text)
    {
        for(Journal j : data)
        {
            if (j.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function returns the index of the Journal Title in the Journal ArrayList.
     * @param  data The arrayList of Journal Objects
     * @param title the title that is being searched for
     */
    int getIndexOfJournalTitle(ArrayList<Journal> data, String title)
    {
        for(Journal j : data)
        {
            if (j.getName().equals(title)) {
                return data.indexOf(j);
            }
        }
        return -1;
    }

    /**
     * This function returns the index of the Journal Text in the Journal ArrayList.
     * @param  data The arrayList of Journal Objects
     * @param text the text that is being searched for
     */
    int getIndexOfJournalText(ArrayList<Journal> data, String text)
    {
        for(Journal j : data)
        {
            if (j.getText().equals(text)) {
                return data.indexOf(j);
            }
        }
        return -1;
    }
}