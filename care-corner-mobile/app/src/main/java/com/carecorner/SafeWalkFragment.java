package com.carecorner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.carecorner.api.JourneyApi;


public class SafeWalkFragment extends Fragment implements View.OnClickListener{
    private NavController safeWalkNavController;

    //UI variables
    Button btnStartWalk, btnWalking, btnArrived;
    EditText destinationEntryBox, etaEntryBox;


    public SafeWalkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_safe_walk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);


        safeWalkNavController = Navigation.findNavController(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //Panic Mode Activation Button is pressed
            case R.id.btnStartWalk:
                startWalk();
                break;

            //Panic Mode Activation Button is pressed
            case R.id.btnWalking:
                continueWalk();
                break;

            //Panic Mode Activation Button is pressed
            case R.id.btnArrived:
                endWalk();
                break;
        }
    }

    private void initViews(View view) {
        btnStartWalk = view.findViewById(R.id.btnStartWalk);
        btnArrived = view.findViewById(R.id.btnArrived);
        btnWalking = view.findViewById(R.id.btnWalking);
        destinationEntryBox = view.findViewById(R.id.destinationEntryBox);
        etaEntryBox = view.findViewById(R.id.etaEntryBox);
    }

    private void startWalk() {
        String destination = destinationEntryBox.getText().toString();
        String eta = etaEntryBox.getText().toString();

        CareCornerApplication.getSession().setArmedWalkState(true);
        JourneyApi.bonVoyage(destination, eta, "80.00", "30.00");
    }


    private void continueWalk() {
        JourneyApi.wayPoint("80.00", "30.00");
    }


    private void endWalk() {
        CareCornerApplication.getSession().setArmedWalkState(false);
        JourneyApi.arrived("80.00", "30.00");
    }






}