package com.carecorner.util;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.carecorner.CareCornerApplication;

import org.json.JSONObject;

import okhttp3.Response;

public class JourneyApi {

    public static void bonVoyage(String destination, String eta, String latitude, String longitude) {
        String journeyUrl = CareCornerApplication.getApiRoute("journey");

        JSONObject bonVoyage = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            bonVoyage.put("user-id", userId);
            bonVoyage.put("destination", destination);
            bonVoyage.put("eta", eta);
            bonVoyage.put("latitude", latitude);
            bonVoyage.put("longitude", longitude);
        } catch (Exception error) {
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

    public static void wayPoint(String latitude, String longitude) {
        String journeyUrl = CareCornerApplication.getApiRoute("journey");
        JSONObject location = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            location.put("user-id", userId);
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

    public static void arrived(String latitude, String longitude) {
        String journeyUrl = CareCornerApplication.getApiRoute("journey/destination");
        JSONObject arrival = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            arrival.put("user-id", userId);
            arrival.put("latitude", latitude);
            arrival.put("longitude", longitude);
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
}