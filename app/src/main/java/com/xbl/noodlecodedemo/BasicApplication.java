package com.xbl.noodlecodedemo;

import android.app.Application;

import com.xbl.noodlecodedemo.db.DaoFactory;

public class BasicApplication extends Application {
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = AppDatabase.getInstance(this);
        DaoFactory.setUserDao(appDatabase.userDao());
    }
}
