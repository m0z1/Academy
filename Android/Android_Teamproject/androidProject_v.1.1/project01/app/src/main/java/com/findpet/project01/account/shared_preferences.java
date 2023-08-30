package com.findpet.project01.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class shared_preferences {
    static String Username = "username";

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(Username, "");
    }

    public static void setUsername(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Username, username);
        editor.commit();
    }
    public static void clearUsername(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

    
}
