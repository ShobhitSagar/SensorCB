package com.example.shobhitsagar.codingblocks101;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("servicetag", "onCreate : ");

        for (int i = 0; i <10; i++) {
            Log.d("servicetag", "Message : "+ i);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service Tag", "onBind");
        return null;
    }
}
