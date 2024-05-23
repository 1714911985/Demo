package com.example.demo.main.fourth.androidcommunication.utils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

public class AnotherActivity extends AppCompatActivity {
    private Button btnReleaseEventBusMessage;
    private Button btnReleaseRxBusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        btnReleaseEventBusMessage = findViewById(R.id.btn_release_event_bus_message);
        btnReleaseEventBusMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusUtil.post(new MessageEvent("这是通过EventBus发布的消息"));
                finish();
            }
        });

        btnReleaseRxBusMessage = findViewById(R.id.btn_release_rx_bus_message);
        btnReleaseRxBusMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post(new WeatherEvent("001", "郑州", "33度"));
                finish();
            }
        });
    }
}