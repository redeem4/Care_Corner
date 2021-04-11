package com.carecorner;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;

public class CareCornerApplication extends Application {
    public String apiUlr = "";

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
