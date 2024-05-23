package com.example.demo.main.second.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;

public class WhatIsFragmentActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowWhatIsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_fragment);
        initData();
        initView();

        wvShowWhatIsFragment.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvShowWhatIsFragment.getTitle());
            }
        });

        wvShowWhatIsFragment.loadUrl("https://blog.csdn.net/sun976649289/article/details/79467577");
    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvShowWhatIsFragment = findViewById(R.id.wv_show_what_is_fragment);
    }

    private void initData() {

    }
}