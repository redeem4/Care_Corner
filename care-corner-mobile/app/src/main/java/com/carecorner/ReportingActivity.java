package com.carecorner;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ReportingActivity extends AppCompatActivity implements ReportingAdapter.ItemClickListener, AddReportingDialog.AddReportingDialogListener {

    ArrayList<Reporting> thedata;
    ReportingAdapter theadapter;
    //private Button btnDelete, btnCreate;

    //incident_list variables
    private Vector<Incident> incidents_list;
    private IncidentListService incidentListService;
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporting_activity);

        //place in onCreated
        Intent incidentListIntent = new Intent(this, IncidentListService.class);
        this.bindService(incidentListIntent, incidentFilerConnection, this.BIND_AUTO_CREATE);
        incidentListService = new IncidentListService();
        incidents_list = new Vector<Incident>();
        //initViews();
        setupRecyclerView();
        //btnCreate.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openDialog();
        //    }
        // });


         }

        /**
         * Manages what happens when a Journal Entry is clicked on.
         * @param  view  This variable is needed for the onClickListener to work
         * @param  position The location of the Journal Entry in the RecyclerView
         */
        @Override
        public void onItemClick (View view,int position){
            saveArrayList(thedata, "reporting");
            Intent intent = new Intent(ReportingActivity.this, ReportingReader.class);
            intent.putExtra("reportingName", theadapter.getItem(position).getId());
            //intent.putExtra("text", theadapter.getItem(position).getText());
            // intent.putExtra("position", position);
            startActivity(intent);
        }

        /**
         * This function deletes the journal entry from the RecyclerView.
         * @param  position The location of the Journal Entry in the RecyclerView to be removed
         */
        @Override
        public void onDeleteBtnClick ( int position){
            thedata.remove(position);
            theadapter.notifyItemRemoved(position);
        }

        /**
         * Sets up the Add Journal Entry Dialog.
         */
        public void openDialog () {
            AddReportingDialog addReportingEntry = new AddReportingDialog();
            addReportingEntry.show(getSupportFragmentManager(), "example dialog");
        }


        @Override
        public void applyTexts (String reportingName){
            int insertIndex = thedata.size();
            if (reportingName.equals("")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
                reportingName = formatter.format(date);
            }

            int maxLength = 14;
            if (reportingName.length() >= maxLength) {
                reportingName = reportingName.substring(0, maxLength - 1) + "...";
            }

            Reporting temp = new Reporting(reportingName);
            thedata.add(insertIndex, temp);
            theadapter.notifyItemInserted(insertIndex);
        }

        /**
         * This function sets up the RecyclerView.
         */
        private void setupRecyclerView () {
            loadIncidents();
            thedata = loadArrayList("reporting");

            /*
            if (thedata == null || thedata.isEmpty()) {
                // Test data to populate the RecyclerView with if the saved Journal List is empty.
                // Mainly for demonstration purposes.
                thedata = new ArrayList<Reporting>();
                thedata.add(new Reporting("test 3/13/2021"));
                thedata.add(new Reporting("test 3/14/2021"));
                thedata.add(new Reporting("test 3/15/2021"));

            }
            */


            // set up the RecyclerView
            RecyclerView recyclerView2 = findViewById(R.id.recycler_view2);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView2.setLayoutManager(layoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView2.getContext(),
                    layoutManager.getOrientation());
            recyclerView2.addItemDecoration(dividerItemDecoration);
            theadapter = new ReportingAdapter(this, incidents_list, this);
            theadapter.setClickListener(this);
            recyclerView2.setAdapter(theadapter);
            applyEdits();
        }

        /**
         * This function handles all the logic for receiving data from intents and
         * adding/updating the Journal Recycler View and the Journal ArrayList.
         */
        private void applyEdits () {
            String reportingTitle = getIntent().getStringExtra("title");
            int thePositionOfOriginalEntry = getIntent().getIntExtra("position", -1);

            if(reportingTitle != ""
                    && reportingTitle != null )
            {
                if(findReportingTitle(thedata, reportingTitle))
                {
                    int index = getIndexOfReportingTitle(thedata, reportingTitle);
                    thedata.get(index).setName(reportingTitle);
                    theadapter.notifyDataSetChanged();
                }
            }
        }


    /**
     * Connects and initializes every element in the layout to a variable.
     */
        /*
        private void initViews() {
            btnDelete = findViewById(R.id.btnDelete2);
            btnCreate = findViewById(R.id.btnCreate2);
        }*/

        /**
         * Overrides the Back Button functionality to return to the Journal Main Menu.
         */
        @Override
        public void onBackPressed ()
        {
            super.onBackPressed();
            saveArrayList(thedata, "reporting");
            Intent intent = new Intent(ReportingActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }


        public void saveArrayList (ArrayList < Reporting > thedata, String thekey){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(thedata);
            editor.putString(thekey, json);
            editor.apply();
        }


        public ArrayList<Reporting> loadArrayList (String thekey){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            Gson gson = new Gson();
            String json = prefs.getString(thekey, null);
            Type type = new TypeToken<ArrayList<Reporting>>() {
            }.getType();
            return gson.fromJson(json, type);
        }


        boolean findReportingTitle (ArrayList < Reporting > thedata, String title)
        {
            for (Reporting j : thedata) {
                if (j.getName().equals(title)) {
                    return true;
                }
            }
            return false;
        }



        int getIndexOfReportingTitle (ArrayList < Reporting > thedata, String title)
        {
            for (Reporting j : thedata) {
                if (j.getName().equals(title)) {
                    return thedata.indexOf(j);
                }
            }
            return -1;
        }

    private ServiceConnection incidentFilerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IncidentListService.IncidentListBinder binder = (IncidentListService.IncidentListBinder) service;
            incidentListService = binder.getService();

            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    private void loadIncidents(){
        incidents_list = incidentListService.loadIncidents(this);
    }

    private void saveIncident() {
        incidentListService.saveIncident(this, incidents_list);
    }

}

