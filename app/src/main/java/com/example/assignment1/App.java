package com.example.assignment1;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.assignment1.Utilities.SharePreferencesManager;
import com.example.assignment1.Utilities.SignalManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SignalManager.init(this);
        SharePreferencesManager.init(this);
    }
}
