package com.example.demo.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;
import com.example.demo.utils.MySQLiteHelper;

public class CheckEmailActivity extends AppCompatActivity {
    private static final String TAG = "CheckEmailActivity";
    private EditText etEmail;
    private Button btnNext;
    private MySQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_email);
        Log.e(TAG,"onCreate");
        initView();
        initData();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                SQLiteDatabase db = helper.getReadableDatabase();
                String sql = "select id from User where email = ?";
                Cursor query = db.rawQuery(sql, new String[]{email});
                if (query.getCount() == 0) {
                    Toast.makeText(CheckEmailActivity.this, "该邮箱没有绑定账号", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CheckEmailActivity.this, ChangePasswordActivity.class);
                    //Log.d("CheckEmailActivity", query.getInt(query.getColumnIndex("id")) + "");
                    if (query.moveToFirst()){
                        intent.putExtra("id", query.getInt(query.getColumnIndex("id")));
                    }

                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        helper = new MySQLiteHelper(this, "Login.db", null, 1);
    }

    private void initView() {
        etEmail = findViewById(R.id.et_email);
        btnNext = findViewById(R.id.btn_next);
    }
}