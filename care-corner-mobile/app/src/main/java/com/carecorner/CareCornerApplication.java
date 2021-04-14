package com.carecorner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;

public class CareCornerApplication extends Application {
    String api = "http://10.0.2.2:4566/restapis/72tu10ue4m/local/_user_request_";

    @Override
    public void onCreate() {
        super.onCreate();
        //AndroidNetworking.initialize(getApplicationContext());

    }
}
