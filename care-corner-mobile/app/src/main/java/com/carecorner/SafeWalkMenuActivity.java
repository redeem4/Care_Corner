package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONObject;

import okhttp3.Response;

public class SafeWalkMenuActivity extends AppCompatActivity {
    Button btnStartWalk, btnWalking, btnArrived;
    EditText destinationEntryBox, etaEntryBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_walk_menu_activity);
        initViews();

        btnStartWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWalk();
            }
        });

        btnWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueWalk();
            }
        });

        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endWalk();
            }
        });
    }

    private void initViews() {
        btnStartWalk = findViewById(R.id.btnStartWalk);
        btnArrived = findViewById(R.id.btnArrived);
        btnWalking = findViewById(R.id.btnWalking);
        destinationEntryBox = findViewById(R.id.destinationEntryBox);
        etaEntryBox = findViewById(R.id.etaEntryBox);
    }


    private void startWalk() {
        String journeyUrl = CareCornerApplication.getApiRoute("journey");

        String destination = destinationEntryBox.getText().toString();
        String eta = etaEntryBox.getText().toString();
        JSONObject bonVoyage = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            bonVoyage.put("user-id", userId);
            bonVoyage.put("destination", destination);
            bonVoyage.put("eta", eta);
            bonVoyage.put("latitude", "80.00");
            bonVoyage.put("longitude", "35.00");
        } catch(Exception error) {
            Log.e("Login:", "Issue creating destination Json");
        }

        AndroidNetworking.post(journeyUrl)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(bonVoyage)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        if (response.isSuccessful()) {
                        } else {
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.getResponse().toString());
                    }
                });
    }


    private void continueWalk() {
        String journeyUrl = CareCornerApplication.getApiRoute("journey");
        JSONObject location = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            location.put("user-id", userId);
            location.put("location", "889 Updated address");
            location.put("latitude", "80.00");
            location.put("longitude", "35.00");
        } catch(Exception error) {
            Log.e("Login:", "Issue creating location Json");
        }

        AndroidNetworking.put(journeyUrl)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(location)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        if (response.isSuccessful()) {
                        } else {
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.getResponse().toString());
                    }
                });
    }


    private void endWalk() {
        String journeyUrl = CareCornerApplication.getApiRoute("journey/destination");
        JSONObject arrival = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            arrival.put("user-id", userId);
            arrival.put("latitude", "80.00");
            arrival.put("longitude", "35.00");
        } catch(Exception error) {
            Log.e("Login:", "Issue creating arrival Json");
        }

        AndroidNetworking.post(journeyUrl)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(arrival)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        if (response.isSuccessful()) {
                        } else {
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.getResponse().toString());
                    }
                });
    }

    /**
     * Overrides the Back Button functionality to return to the welcome screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(SafeWalkMenuActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}