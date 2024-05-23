package com.example.demo.main.first.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;

public class HowToCreateActivityActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowHowToCreateActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_create_activity);
        initData();
        initView();
        wvShowHowToCreateActivity.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvShowHowToCreateActivity.getTitle());
            }
        });
    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvShowHowToCreateActivity = findViewById(R.id.wv_show_how_to_create_activity);
        wvShowHowToCreateActivity.loadUrl("https://blog.csdn.net/u012005313/article/details/46970401");
    }

    private void initData() {


    }
}