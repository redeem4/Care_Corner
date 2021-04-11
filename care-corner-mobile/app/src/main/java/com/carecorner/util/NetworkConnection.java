package com.carecorner.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkConnection {

    public static boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection connection = (HttpURLConnection)
                        (new URL("http://localstack:4566/heatlh")
                                .openConnection());
                connection.setConnectTimeout(1500);
                connection.setRequestMethod("GET");
                connection.connect();
                Log.e("Network", connection.getResponseMessage());
                if (connection.getResponseCode() == 200) {
                    Log.d("Network:", "Successfully connected to internet");
                    return true;
                }
            } catch (IOException e) {
                Log.e("Network:", "Error checking internet connection", e);
            }
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
