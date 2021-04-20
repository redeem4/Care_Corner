package com.carecorner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ToggleButton;


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


        panicNavController = Navigation.findNavController(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //Panic Mode Activation Button is pressed
            case R.id.activate_btn:
                if(panic_activated){
                    deactivatePanicUI();}
                else{activatePanicUI();}
                break;

            //Panic Mode Activation Button is pressed
            case R.id.panic_incident_btn:
                //linear layour button is pressed
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
}