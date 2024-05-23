package com.example.demo.main.third.filesave;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.demo.R;


public class DataStoreActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowDataStore;
//    private final Preferences.Key<Integer> SAMPLE_KEY = new Preferences.Key<Integer>("sample_key");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_store);
        initData();
        initView();

        wvShowDataStore.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWatingWebView.setVisibility(View.INVISIBLE);
                tvWebViewTitle.setText(wvShowDataStore.getTitle());
            }
        });

//        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();
//
//        //写
//        dataStore.updateDataAsync(new Function<Preferences, Single<Preferences>>() {
//            @Override
//            public Single<Preferences> apply(Preferences preferences) throws Exception {
//                MutablePreferences mutablePreferences = preferences.toMutablePreferences();
//                mutablePreferences.set(SAMPLE_KEY, 1);
//                return Single.just(mutablePreferences);
//            }
//        });
//
//        //读
//        Flowable<Integer> flowable = dataStore.data().map(preferences -> preferences.get(SAMPLE_KEY));


    }

    private void initView() {
        tvWebViewTitle = findViewById(R.id.tv_webview_title);
        pbWatingWebView = findViewById(R.id.pb_wating_webview);
        wvShowDataStore = findViewById(R.id.wv_show_datastore);
        wvShowDataStore.loadUrl("https://developer.android.google.cn/topic/libraries/architecture/datastore?hl=zh-cn#kts");
    }

    private void initData() {

    }
}
