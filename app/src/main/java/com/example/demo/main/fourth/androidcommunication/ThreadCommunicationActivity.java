package com.example.demo.main.fourth.androidcommunication;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

public class ThreadCommunicationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnHandler;
    private Button btnHandlerThread;
    private HandlerThread handlerThread;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Toast.makeText(ThreadCommunicationActivity.this, "通过Handler进行线程间通信", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Handler threadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_communication);
        initView();
        initData();
        Toolbar toolbar = findViewById(R.id.tb_thread);
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
        handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();
        threadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 2) {
                    Toast.makeText(ThreadCommunicationActivity.this, "通过HandlerThread进行线程间通信", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void initView() {
        btnHandler = findViewById(R.id.btn_handler);
        btnHandler.setOnClickListener(this);
        btnHandlerThread = findViewById(R.id.btn_handler_thread);
        btnHandlerThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_handler) {
            useHandler();
        } else if (v.getId() == R.id.btn_handler_thread) {
            useHandlerThread();
        }
    }

    private void useHandlerThread() {
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 2;
                threadHandler.sendMessage(message);
            }
        });
    }

    private void useHandler() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        });
    }
}