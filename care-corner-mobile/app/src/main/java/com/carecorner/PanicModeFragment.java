package com.carecorner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Vector;


public class PanicModeFragment extends Fragment implements View.OnClickListener {

    private NavController panicNavController;

    //panic button and its states
    private ToggleButton activate_btn;
    private static final String DEACTIVATED_TEXT = "Activate";
    private static final String ACTIVATED_TEXT = "Deactivate";
    private static final boolean  PANIC_ACTIVATED = true;
    private static final boolean  PANIC_DEACTIVATED = false;
    private boolean panic_activated = false;

    private TextView panic_status;
    private static final String PANIC_STATUS_ACTIVATED = "Panic Mode is currently activated.";
    private static final String PANIC_STATUS_DEACTIVATED = "Panic Mode is currently deactivated.";

    private TextView panic_status2;
    private static final String PANIC_STATUS_ACTIVATED2 = "To deactivate, hold down the button.";
    private static final String PANIC_STATUS_DEACTIVATED2 = "To activate, hold down the button.";

    private Chronometer panic_timer;
    private LinearLayout incidents_linear_layout;
    private ImageButton incidents_btn;
    private Incident current_incident;
    private Vector<Incident> incidents_list;

    //TODO - delete this code here for testing
    private TextView incident_report;
    private int incident_count;
    int i;



    public PanicModeFragment() {
        // Required empty public constructor
    }

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_panic_mode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //link variables to widgets
        activate_btn = view.findViewById(R.id.activate_btn);
        panic_status = view.findViewById(R.id.panic_status);
        panic_status2 = view.findViewById(R.id.panic_status2);
        panic_timer = view.findViewById(R.id.panic_timer);
        incidents_linear_layout = view.findViewById(R.id.panic_incident_linear_layout);
        incidents_btn = view.findViewById(R.id.panic_incident_btn);

        //TODO - delete this code here for testing
        incident_report = view.findViewById(R.id.incident_report);

        //set up panic_btn text when on/off
        activate_btn.setTextOff(DEACTIVATED_TEXT);
        activate_btn.setTextOn(ACTIVATED_TEXT);

        //set panic to deactivated state
        activate_btn.setChecked(PANIC_DEACTIVATED);
        panic_status.setText(PANIC_STATUS_DEACTIVATED);
        panic_status2.setText(PANIC_STATUS_DEACTIVATED2);
        panic_timer.setVisibility(View.GONE);

        //set click listeners
        activate_btn.setOnClickListener(this);
        incidents_btn.setOnClickListener(this);

        loadIncidents();
        //TODO - delete this code here for testing
        incident_count = incidents_list.size();
        i=0;
        incident_report.setText("There were " + incident_count + " incidents loaded");


        panicNavController = Navigation.findNavController(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //Panic Mode Activation Button is pressed
            case R.id.activate_btn:
                if(panic_activated){
                    deactivatePanicUI();
                    askIfIncident();
                }else{
                    activatePanicUI();
                    current_incident = new Incident();
                }
                break;

            //Panic Mode Activation Button is pressed
            case R.id.panic_incident_btn:
                //incidents button is pressed
                String test_string = (incidents_list.get(i)).toString();
                i = ((i + 1) % incident_count);
                incident_report.setText(test_string);

                break;
        }
    }

    //handles UI elements when Panic mode is activated
    private void activatePanicUI() {
        panic_activated = PANIC_ACTIVATED;
        activate_btn.setChecked(PANIC_ACTIVATED);
        panic_status.setText(PANIC_STATUS_ACTIVATED);
        panic_status2.setText(PANIC_STATUS_ACTIVATED2);
        panic_timer.setBase(SystemClock.elapsedRealtime());
        panic_timer.setVisibility(View.VISIBLE);
        incidents_linear_layout.setVisibility(View.GONE);
        panic_timer.start();
    }

    //handles UI elements when Panic mode is activated
    private void deactivatePanicUI(){
        panic_activated = PANIC_DEACTIVATED;
        activate_btn.setChecked(PANIC_DEACTIVATED);
        panic_status.setText(PANIC_STATUS_DEACTIVATED);
        panic_status2.setText(PANIC_STATUS_DEACTIVATED2);
        panic_timer.setVisibility(View.GONE);
        incidents_linear_layout.setVisibility(View.VISIBLE);
        panic_timer.stop();
    }

    public AlertDialog askIfIncident()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Incident:")
                .setMessage("Would you like to save this incident?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: save incident to shared folder
                        incidents_list.add(current_incident);
                        saveIncident();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: go to home page
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    private void saveIncident() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(incidents_list);
        editor.putString(getContext().getString(R.string.incident_list_key), json);
        editor.apply();
    }

    private void loadIncidents(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(getContext().getString(R.string.incident_list_key), null);
        Type type = new TypeToken<Vector<Incident>>() {}.getType();
        incidents_list = gson.fromJson(json, type);

        if (incidents_list == null) {
            incidents_list = new Vector<Incident>();
        }
    }
}