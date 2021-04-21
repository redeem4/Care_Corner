package com.carecorner.api;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.carecorner.CareCornerApplication;
import com.google.gson.JsonArray;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Response;

public class ContactApi {
    private static JSONArray contacts = new JSONArray();

    public static JSONArray allContacts() {
        String userId = CareCornerApplication.getSession().getUserId();
        String contactUrl = CareCornerApplication.getApiRoute("contacts/" + userId);

        AndroidNetworking.get(contactUrl)
                .addHeaders("Content-Type", "application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Response {}:", response.toString());
                            JSONObject data = response.getJSONObject("data");
                            contacts = data.getJSONArray("contacts");
                        } catch (Exception error) {

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.toString());
                    }
                }) ;
        return contacts;
    }

    public static void updateContacts(JSONArray contacts) {
        String userId = CareCornerApplication.getSession().getUserId();
        String contactUrl = CareCornerApplication.getApiRoute("contacts/" + userId);

        AndroidNetworking.put(contactUrl)
                .addHeaders("Content-Type", "application/json")
                .addJSONArrayBody(contacts)
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