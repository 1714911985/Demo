package com.example.demo.main.third.service.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Author: Eccentric
 * Created on 2024/5/16 10:26.
 * Description: com.example.demo.main.third.service.myservice.MyIntentService
 */
public class MyIntentService extends IntentService {
    private final static String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
