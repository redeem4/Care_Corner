package com.carecorner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.carecorner.util.Session;

public class CareCornerApplication extends Application {
    private static final String apiCode = "qw48frw5i7";
    private static String api = "http://10.0.2.2:4566/restapis/%s/local/_user_request_/api/";
    private static Session session;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.api = String.format(api, apiCode);
        this.context = getApplicationContext();
        this.session = new Session(this.context);
        //AndroidNetworking.initialize(getApplicationContext());
    }

    public static String getApiRoute(String resource) {
        return CareCornerApplication.api + resource;
    }

    public static Context getAppContext() {
        return CareCornerApplication.context;
    }

    public static Session getSession() {
        return CareCornerApplication.session;
    }
}
