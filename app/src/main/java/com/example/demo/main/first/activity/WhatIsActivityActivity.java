package com.example.demo.main.first.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;

public class WhatIsActivityActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowWhatIsActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_activity);
        initData();
        initView();

        wvShowWhatIsActivity.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvShowWhatIsActivity.getTitle());
            }
        });
    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvShowWhatIsActivity = findViewById(R.id.wv_show_what_is_activity);
        wvShowWhatIsActivity.loadUrl("https://www.cnblogs.com/wxishang1991/p/5223863.html");
    }

    private void initData() {

    }
}
