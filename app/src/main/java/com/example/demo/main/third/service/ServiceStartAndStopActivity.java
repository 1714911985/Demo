package com.example.demo.main.third.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;
import com.example.demo.main.third.service.myservice.MyService;

public class ServiceStartAndStopActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStartService;
    private Button btnStopService;
    private Button btnBindService;
    private Button btnUnbindService;
    private Context context;
    private ServiceConnection serviceConnection;
    private MyService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_start_and_stop);

        initView();
        initData();
        Toolbar toolbar = findViewById(R.id.tb_service_start_and_stop);
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
        context = this;
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                downloadBinder = (MyService.DownloadBinder) service;
                downloadBinder.startDownload();
                downloadBinder.getProgress();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    private void initView() {
        btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(this);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnStopService.setOnClickListener(this);
        btnBindService = findViewById(R.id.btn_bind_service);
        btnBindService.setOnClickListener(this);
        btnUnbindService = findViewById(R.id.btn_unbind_service);
        btnUnbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_service) {
            startService(new Intent(context, MyService.class));
        } else if (v.getId() == R.id.btn_stop_service) {
            stopService(new Intent(context, MyService.class));
        } else if (v.getId() == R.id.btn_bind_service) {
            bindService(new Intent(context, MyService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.btn_unbind_service) {
            unbindService(serviceConnection);
        }
    }
}