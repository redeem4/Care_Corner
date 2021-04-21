package com.carecorner.api;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.carecorner.CareCornerApplication;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Response;

public class ContactApi {

    public static void allContacts() {
        String journeyUrl = CareCornerApplication.getApiRoute("contacts");

        JSONObject packet = new JSONObject();
        try {
            String userId = CareCornerApplication.getSession().getUserId();
            packet.put("user-id", userId);
        } catch (Exception error) {
            Log.e("All Contacts:", "Issue creating all contacts Json");
        }

        AndroidNetworking.post(journeyUrl)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(packet)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("Contacts: ", response.getString(0));
                        } catch (Exception error) {

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.getResponse().toString());
                    }
                }) ;
    }
}