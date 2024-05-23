package com.example.demo.main.second.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;
import com.example.demo.main.second.broadcast.receiver.MySecondReceiver;

public class CreateBroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSendDisorderBroadcast;
    private Button btnSendOrderlyBroadcast;
    private MySecondReceiver mySecondReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_broadcast_receiver);

        initView();
        initData();
        Toolbar toolbar = findViewById(R.id.tb_broadcast);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        mySecondReceiver = new MySecondReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.demo.MY_BROADCAST_RECEIVER");
        intentFilter.setPriority(10);
        registerReceiver(mySecondReceiver,intentFilter);
    }

    private void initView() {
        btnSendDisorderBroadcast = findViewById(R.id.btn_send_disorder_broadcast);
        btnSendDisorderBroadcast.setOnClickListener(this);
        btnSendOrderlyBroadcast = findViewById(R.id.btn_send_orderly_broadcast);
        btnSendOrderlyBroadcast.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mySecondReceiver);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_disorder_broadcast){
            Intent intent = new Intent("com.example.demo.MY_BROADCAST_RECEIVER");
            sendBroadcast(intent);
        }else if (v.getId() == R.id.btn_send_orderly_broadcast){
            Intent intent = new Intent("com.example.demo.MY_BROADCAST_RECEIVER");
            sendOrderedBroadcast(intent,null);
        }
    }
}