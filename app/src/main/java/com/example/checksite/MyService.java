package com.example.checksite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public CheckSite checkSite = null;
    final String LOG_TAG = "myLogs";
    public void onCreate()
    {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra("Url");
        Log.d(LOG_TAG, url);
        checkSite = new CheckSite(url);
        Log.d(LOG_TAG, "onStart");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void someTask()
    {
        checkSite.execute();
    }


}
