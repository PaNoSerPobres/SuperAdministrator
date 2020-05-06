package com.example.superadministrator;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;

public class SuperAdministratorApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppEventsLogger.activateApp(this);
    }
}
