package com.carecorner;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;

public class CareCornerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
