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
        String journeyUrl = CareCornerApplication.getApiRoute("contacts/" + userId);

        AndroidNetworking.get(journeyUrl)
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
}