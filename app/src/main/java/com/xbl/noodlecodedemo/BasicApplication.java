package com.xbl.noodlecodedemo;

import android.app.Application;

public class BasicApplication extends Application {
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = AppDatabase.getInstance(this);
    }
}
