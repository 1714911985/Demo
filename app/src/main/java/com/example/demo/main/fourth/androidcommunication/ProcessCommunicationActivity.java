package com.example.demo.main.fourth.androidcommunication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.anotherprocess.IMyService;
import com.example.demo.R;

public class ProcessCommunicationActivity extends AppCompatActivity {
    private Button btnBroadcastReceiver;
    private Button btnAidl;
    private IMyService iMyService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                iMyService = IMyService.Stub.asInterface(service);
                iMyService.sendMessage("这是另一个进程通过AIDL发送的信息");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_communication);

        initView();
        Toolbar toolbar = findViewById(R.id.tb_process);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBroadcastReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("广播A");
                intent.putExtra("name", "小明");
                sendBroadcast(intent);
            }
        });

        btnAidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.anotherprocess", "com.example.anotherprocess.MyService"));
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    private void initView() {
        btnAidl = findViewById(R.id.btn_aidl);
        btnBroadcastReceiver = findViewById(R.id.btn_broadcast_receiver);
    }
}