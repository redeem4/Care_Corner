package com.carecorner;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.IBinder;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.carecorner.RecorderService.RecorderBinder;

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

    //incident_list variables
    private Vector<Incident> incidents_list;
    private IncidentListService incidentListService;
    private boolean isBound;

    //TODO - delete this code here for testing
    private TextView incident_report;
    private int incident_count;
    int i;

    //Media Recorder Variables
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;
    private String recordFile;
    private RecorderService recorderService;
    private boolean isRecording = false;



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

        //place in onCreated
        Intent incidentListIntent = new Intent(getContext(), IncidentListService.class);
        getContext().bindService(incidentListIntent, incidentFilerConnection, Context.BIND_AUTO_CREATE);
        incidentListService = new IncidentListService();

        Intent recorderIntent = new Intent(getContext(), RecorderService.class);
        getContext().bindService(recorderIntent, recorderConnection, Context.BIND_AUTO_CREATE);
        recorderService = new RecorderService();



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
                    recorderService.startRecording(current_incident.getRecording_file_name());
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
                        incident_count++;
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
        incidentListService.saveIncident(getContext(), incidents_list);
    }

    private void loadIncidents(){
        incidents_list = incidentListService.loadIncidents(getContext());

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

    private ServiceConnection recorderConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //IncidentListService.IncidentListBinder binder = (IncidentListService.IncidentListBinder) service;
            RecorderService.RecorderBinder binder = (RecorderService.RecorderBinder) service;
            //incidentListService = binder.getService();
            recorderService = binder.getService();

            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    //Rec_requirement
    private void start_audio_recording() {
        if (checkPermissions()) {
            //TODO create recorder Intent in onCreate

            recorderService.startRecording(" ");

            isRecording = true;
        }
    }
    //Rec_requirement
    private void stop_audio_recording() {
        recorderService.stopRecording();
        //stopRecording();


        isRecording = false;
    }

    //TODO move permission code to fake Phone Screen
    private boolean checkPermissions() {
        if(ActivityCompat.checkSelfPermission(getActivity(), recordPermission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }

    }
}