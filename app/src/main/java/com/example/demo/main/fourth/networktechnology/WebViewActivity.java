package com.example.demo.main.fourth.networktechnology;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView wvShowWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        initData();

        wvShowWebView.loadUrl("https://www.jd.com/");
    }

    private void initData() {
    }

    private void initView() {
        wvShowWebView = findViewById(R.id.wv_show_web_view);
    }
}