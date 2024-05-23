package com.example.demo.main.fourth.networktechnology;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTheNetworkUsingHttpProtocolActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnHttpUrlConnect;
    private Button btnOkHttp;
    private TextView tvResponseText;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                tvResponseText.setText(msg.getData().getString("text"));
            } else if (msg.what == 2) {
                tvResponseText.setText(msg.getData().getString("text"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_the_network_using_http_protocol);

        initView();
        initData();
        Toolbar toolbar = findViewById(R.id.tb_http_protocol);
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
    }

    private void initView() {
        btnHttpUrlConnect = findViewById(R.id.btn_http_url_connect);
        btnHttpUrlConnect.setOnClickListener(this);
        btnOkHttp = findViewById(R.id.btn_ok_http);
        btnOkHttp.setOnClickListener(this);
        tvResponseText = findViewById(R.id.tv_response_text);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_http_url_connect) {
            sendRequestWithHttpURLConnection();
        } else if (v.getId() == R.id.btn_ok_http) {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(() -> {
            HttpURLConnection httpURLConnection;
            try {
                StringBuilder builder = new StringBuilder();
                URL url = new URL("https://www.baidu.com");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(8000);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("text", builder.toString());
                msg.setData(bundle);
                handler.sendMessage(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.bilibili.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    if (responseData != null) {
                        Message message = new Message();
                        message.what = 2;
                        Bundle bundle = new Bundle();
                        bundle.putString("text", responseData);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}