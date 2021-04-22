package com.carecorner;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//TODO move service Imports
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.widget.TextView;
import android.widget.Toast;

import com.carecorner.RecorderService.RecorderBinder;



public class RecordFragment extends Fragment implements View.OnClickListener {

    //TODO - service Variables
    private RecorderService recorderService;
    //is this service bound to a client?
    boolean isBound = false;
    private TextView timer;
    //TODO audio recording
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;
    private MediaRecorder mediaRecorder;
    private String recordFile;
    private boolean isRecording = false;


    public void showTime(View view) {
        String currentTime = recorderService.getCurrentTime();
        //String currentTime = "Testing";
        timer.setText(currentTime);
    }

    private NavController navController;

    private ImageButton listBtn;
    private ImageButton recordBtn;



    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO create recorder Intent in onCreate
        Intent recorderIntent = new Intent(getActivity(), RecorderService.class);
        getActivity().bindService(recorderIntent, recorderConnection, Context.BIND_AUTO_CREATE);
        timer = view.findViewById(R.id.record_timer);

        navController = Navigation.findNavController(view);
        listBtn = view.findViewById(R.id.record_list_btn);
        recordBtn = view.findViewById(R.id.record_button);




        listBtn.setOnClickListener(this);
        recordBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.record_list_btn:
                if(isRecording){
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Audio Still Recording");
                    isRecording = false;
                } else {
                    navController.navigate(R.id.action_recordFragment_to_audioListFragment2);
                }
                break;

            case R.id.record_button:
                if(isRecording){
                    //stop recording
                    recorderService.stopRecording();
                    //stopRecording();

                    //swaps image upon button click
                    recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.record_btn_stopped));
                    isRecording = false;
                }else{
                    //start recording
                    if (checkPermissions()) {
                        showTime(v);
                        recorderService.startRecording();
                        //startRecording();
                        recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.record_btn_recording));
                        isRecording = true;
                    }
                }
                break;



        }
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void startRecording() {

        String recordPath = getActivity().getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now = new Date();

        recordFile = "Recording_" + formatter.format(now) + ".3gp";

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();

    }

    //TODO move permission code to fake Phone Screen
    private boolean checkPermissions() {
        if(ActivityCompat.checkSelfPermission(getContext(), recordPermission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(isRecording){
            stopRecording();
        }


    }

    //TODO class responsible for binding to service
    private ServiceConnection recorderConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RecorderBinder binder = (RecorderBinder) service;
            recorderService =  binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}