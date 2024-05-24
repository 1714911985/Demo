package com.example.anotherprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private IMyService.Stub mBinder = new IMyService.Stub() {
        @Override
        public void sendMessage(String msg) {
            Log.e("MyService", msg);
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }
}