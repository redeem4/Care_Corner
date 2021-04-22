package com.carecorner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carecorner.api.JourneyApi;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class SafeWalkMenuFragment extends Fragment implements View.OnClickListener{
    private NavController safeWalkNavController;

    //UI variables
    Button btnStartWalk, btnWalking, btnArrived, btnPanic;
    EditText destinationEntryBox, etaEntryBox;
    TextView txtDestination, txtETA;


    public SafeWalkMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.safe_walk_menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        safeWalkNavController = Navigation.findNavController(view);

    }

    @Override
    public void onClick(View v) {
        Log.d("on Click", "On click");
        switch (v.getId()){
            //Panic Mode Activation Button is pressed
            case R.id.btnStartWalk:
                startWalk();
                break;

            //Panic Mode Activation Button is pressed
         //   case R.id.btnWalking:
          //      continueWalk();
           //     break;

            //Panic Mode Activation Button is pressed
            case R.id.btnArrived:
                endWalk();
                break;
        }
    }

    private void initViews(View view) {
        btnStartWalk = view.findViewById(R.id.btnStartWalk);
        btnArrived = view.findViewById(R.id.btnArrived);
       // btnWalking = view.findViewById(R.id.btnWalking);
        btnPanic = view.findViewById(R.id.btnPanic);
        destinationEntryBox = view.findViewById(R.id.destinationEntryBox);
        etaEntryBox = view.findViewById(R.id.etaEntryBox);
        txtDestination = view.findViewById(R.id.textDestination);
        txtETA = view.findViewById(R.id.textETA);

        btnStartWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWalk();
                ((SafeWalkMenuActivity)getActivity()).expandMap();
            }
        });

        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endWalk();
            }
        });

        btnPanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void startWalk() {
        Log.d("Calling Start Walk API", "Yeah");
        btnStartWalk.setVisibility(View.GONE);
        btnArrived.setVisibility(View.VISIBLE);
        destinationEntryBox.setVisibility(View.GONE);
        etaEntryBox.setVisibility(View.GONE);
        txtETA.setVisibility(View.GONE);
        txtDestination.setVisibility(View.GONE);
        String destination = destinationEntryBox.getText().toString();
        String eta = etaEntryBox.getText().toString();

        CareCornerApplication.getSession().setArmedWalkState(true);
        JourneyApi.bonVoyage(destination, eta, "81.00", "30.00");
    }


    public void continueWalk(String latitude, String longitude) {
        Log.d("Safe Walk:", "Continue Walk");
        JourneyApi.wayPoint(latitude, longitude);
    }


    private void endWalk() {
        Log.d("Safe Walk:", "Ending");
        CareCornerApplication.getSession().setArmedWalkState(false);
        JourneyApi.arrived("80.00", "30.00");
        btnStartWalk.setVisibility(View.VISIBLE);
        btnArrived.setVisibility(View.GONE);
        destinationEntryBox.setVisibility(View.VISIBLE);
        etaEntryBox.setVisibility(View.VISIBLE);
        txtETA.setVisibility(View.VISIBLE);
        txtDestination.setVisibility(View.VISIBLE);
    }






}