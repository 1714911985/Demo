package com.example.demo.main.third.service;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

public class WhatIsServiceActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_service);
        initData();
        initView();

        wvShowService.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvShowService.getTitle());
            }
        });
    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvShowService = findViewById(R.id.wv_show_service);
        wvShowService.loadUrl("https://blog.csdn.net/qq_28595679/article/details/75007324?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171576752216800222837520%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171576752216800222837520&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-75007324-null-null.142^v100^pc_search_result_base8&utm_term=%E4%BB%80%E4%B9%88%E6%98%AFService&spm=1018.2226.3001.4187");
    }

    private void initData() {

    }
}
