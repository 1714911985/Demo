package com.example.demo.main.second.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyFirstReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MyFirstReceiver接收到了发送的广播", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}