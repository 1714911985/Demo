package com.example.demo.main.third.filesave;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;


import com.example.demo.R;

import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;


public class DataStoreActivity extends AppCompatActivity {
    private TextView tvWebViewTitle;
    private ProgressBar pbWatingWebView;
    private WebView wvShowDataStore;
    private final Preferences.Key<Integer> INTEGER_KEY = new Preferences.Key<>("integer_key");

    @SuppressLint("CheckResult")
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

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();

        // 写入数据
        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            Integer currentInt = prefsIn.get(INTEGER_KEY);
            mutablePreferences.set(INTEGER_KEY, currentInt != null ? currentInt + 1 : 1);
            return Single.just(mutablePreferences);
        });

        // 读取数据
        Flowable<Optional<Integer>> flowable = dataStore.data().map(prefs -> Optional.ofNullable(prefs.get(INTEGER_KEY)));
        flowable.subscribe(optionalInteger -> {
            if (optionalInteger.isPresent()) {
                Integer value = optionalInteger.get();
                Log.d("DataStore", "Read integer value: " + value);
            } else {
                Log.d("DataStore", "Integer value not found");
            }
        }, throwable -> {
            // 处理读取数据时出现的错误
            Log.e("DataStore", "Error reading integer value: " + throwable.getMessage());
        });


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
