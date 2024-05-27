package com.example.anotherprocess;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnQueryData;
    private Button btnAddData;
    private Button btnUpdateData;
    private Button btnDeleteData;
    private final static String authority = "com.example.demo.main.fourth.androidcommunication.utils.MyContentProvider";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        final IntentFilter filter = new IntentFilter();
        filter.addAction("广播A");
        BroadcastReceiver mAReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();
                String msg = intent.getStringExtra("name");
                Log.d("AnotherMainActivity", "应用B收到：" + action + ",name:" + msg);
            }
        };
        registerReceiver(mAReceiver, filter);
    }

    private void initData() {
    }

    private void initView() {
        btnDeleteData = findViewById(R.id.btn_delete_data);
        btnDeleteData.setOnClickListener(this);
        btnQueryData = findViewById(R.id.btn_query_data);
        btnQueryData.setOnClickListener(this);
        btnAddData = findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener(this);
        btnUpdateData = findViewById(R.id.btn_update_data);
        btnUpdateData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_query_data) {
            queryByContentProvider();
        }
    }

    private void queryByContentProvider() {
//        Uri uri = Uri.parse("content://" + authority + "/user");
        Uri uri = Uri.parse("content://"+authority+"/user");
        grantUriPermission(authority, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));

                Log.e("AnotherProcess", "id : " + id);
                Log.e("AnotherProcess", "username : " + username);
                Log.e("AnotherProcess", "password : " + password);
                Log.e("AnotherProcess", "email : " + email);
            }
            cursor.close();
        }

    }
}