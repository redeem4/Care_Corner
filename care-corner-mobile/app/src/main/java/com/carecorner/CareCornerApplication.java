package com.carecorner;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;

public class CareCornerApplication extends Application {
    String apiUlr = "http://10.0.2.2:4566/restapis/hq63oz5e90/local/_user_request_";

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
