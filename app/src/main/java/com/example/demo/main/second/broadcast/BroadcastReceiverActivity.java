package com.example.demo.main.second.broadcast;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;

public class BroadcastReceiverActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        initData();
        initView();

        wvBroadcastReceiver.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvBroadcastReceiver.getTitle());
            }
        });
    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvBroadcastReceiver = findViewById(R.id.wv_broadcast_receiver);
        wvBroadcastReceiver.loadUrl("https://blog.csdn.net/zyz18813049204/article/details/82527137?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171515828516800182795276%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171515828516800182795276&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-82527137-null-null.142^v100^pc_search_result_base8&utm_term=broadcast%20receiver&spm=1018.2226.3001.4187");
    }

    private void initData() {

    }
}
