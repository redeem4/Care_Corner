package com.carecorner;

import android.content.res.Resources;
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
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanicFragment extends Fragment implements View.OnClickListener {

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



    public PanicFragment() {
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

        //set up panic_btn states
        activate_btn = view.findViewById(R.id.activate_btn);
        activate_btn.setText(R.string.panic_btn_deactivated);
        activate_btn.setTextOff(DEACTIVATED_TEXT);
        activate_btn.setTextOn(ACTIVATED_TEXT);

        panic_status = view.findViewById(R.id.panic_status);
        panic_status2 = view.findViewById(R.id.panic_status2);
        panic_timer = view.findViewById(R.id.panic_timer);

        //set panic to deactivated state
        activate_btn.setChecked(PANIC_DEACTIVATED);
        panic_status.setText(PANIC_STATUS_DEACTIVATED);
        panic_status2.setText(PANIC_STATUS_DEACTIVATED2);

        //set click listeners
        activate_btn.setOnClickListener(this);




        panicNavController = Navigation.findNavController(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activate_btn:


                if(panic_activated){
                    panic_activated = PANIC_DEACTIVATED;
                    activate_btn.setChecked(PANIC_DEACTIVATED);
                    panic_status.setText(PANIC_STATUS_DEACTIVATED);
                    panic_status2.setText(PANIC_STATUS_DEACTIVATED2);
                    panic_timer.stop();
                }

                else{
                    panic_activated = PANIC_ACTIVATED;
                    activate_btn.setChecked(PANIC_ACTIVATED);
                    panic_status.setText(PANIC_STATUS_ACTIVATED);
                    panic_status2.setText(PANIC_STATUS_ACTIVATED2);
                    panic_timer.setBase(SystemClock.elapsedRealtime());
                    panic_timer.start();
                }
        }
    }
}