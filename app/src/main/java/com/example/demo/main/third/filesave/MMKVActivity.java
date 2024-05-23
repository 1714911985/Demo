package com.example.demo.main.third.filesave;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.example.demo.main.first.activity.auxiliary.MyParcelableObject;
import com.tencent.mmkv.MMKV;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MMKVActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowMMKV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkvactivity);
        initData();
        initView();

        wvShowMMKV.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvShowMMKV.getTitle());
            }
        });

        MMKV.initialize(this);
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode("intKey", 1);
        long longValue = 2L;
        mmkv.encode("longKey", longValue);
        mmkv.encode("floatKey", 3.0f);
        mmkv.encode("doubleKey", 4.0);
        mmkv.encode("booleanKey", true);
        MyParcelableObject myParcelableObject = new MyParcelableObject(1, "zs");
        mmkv.encode("parcelableKey", myParcelableObject);
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        mmkv.encode("byteArrayKey", bytes);
        mmkv.encode("stringKey", "string");
        Set<String> strings = new HashSet<>();
        strings.add("a");
        mmkv.encode("SetStringKey",strings);

        Log.e("intKey", String.valueOf(mmkv.decodeInt("intKey")));
        Log.e("longKey", String.valueOf(mmkv.decodeLong("longKey")));
        Log.e("floatKey", String.valueOf(mmkv.decodeFloat("floatKey")));
        Log.e("doubleKey", String.valueOf(mmkv.decodeDouble("doubleKey")));
        Log.e("parcelableKey", String.valueOf(mmkv.decodeParcelable("parcelableKey", MyParcelableObject.class )));
        Log.e("byteArrayKey", Arrays.toString(mmkv.decodeBytes("byteArrayKey")));
        Log.e("stringKey", String.valueOf(mmkv.decodeString("stringKey")));
        Log.e("SetStringKey", String.valueOf(mmkv.decodeStringSet("SetStringKey")));

    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvShowMMKV = findViewById(R.id.wv_show_mmkv);
        wvShowMMKV.loadUrl("https://blog.csdn.net/weixin_45112340/article/details/131798459?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171567939216800188524065%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171567939216800188524065&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-131798459-null-null.142^v100^pc_search_result_base8&utm_term=MMKV&spm=1018.2226.3001.4187");
    }

    private void initData() {

    }
}
