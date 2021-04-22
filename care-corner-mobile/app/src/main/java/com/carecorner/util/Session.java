package com.carecorner.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences preferences;

    public Session(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserId(String userId) {
        preferences.edit().putString("userId", userId).commit();
    }

    public String getUserId() {
        return preferences.getString("userId", "111");
    }

    public void setArmedWalkState(boolean armed) {
        preferences.edit().putString("armedWalk", Boolean.toString(armed)).commit();
    }

    public Boolean getArmedWalkState() {
        return Boolean.parseBoolean(preferences.getString("armedWalk", "false"));
    }
}
