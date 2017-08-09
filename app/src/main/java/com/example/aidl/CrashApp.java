package com.example.aidl;

import android.app.Application;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class CrashApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Crash crashHandler = Crash.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
