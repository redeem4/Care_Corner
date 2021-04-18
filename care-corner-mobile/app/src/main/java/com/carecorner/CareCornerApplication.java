package com.carecorner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;

public class CareCornerApplication extends Application {
    final String apiCode = "6vdnsy91ut";
    String api = "http://10.0.2.2:4566/restapis/%s/local/_user_request_";

    @Override
    public void onCreate() {
        super.onCreate();
        this.api = String.format(api, apiCode);
        //AndroidNetworking.initialize(getApplicationContext());

    }
}
