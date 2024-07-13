package com.example.assignment1.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesManager {
    private static  volatile SharePreferencesManager instance = null;    // (singleton)

    private static final String SP_FILE = "SP_FILE";
    private SharedPreferences sharedPref;

    private SharePreferencesManager(Context context) {
        this.sharedPref = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
    }

    public static SharePreferencesManager init(Context context) {
        if(instance == null) {
            synchronized (SharePreferencesManager.class){
                if(instance == null){
                    instance = new SharePreferencesManager(context);
                }
            }
        }
        return getInstance();
    }

    public static SharePreferencesManager getInstance() {
        return instance;
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString (String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }
}

