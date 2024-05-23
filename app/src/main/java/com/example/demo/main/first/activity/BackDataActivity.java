package com.example.demo.main.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;

public class BackDataActivity extends AppCompatActivity {
    private EditText edInputUsername;
    private EditText edInputPassword;
    private Button btnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_data);

        initData();
        initView();
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edInputUsername.getText().toString();
                String password = edInputPassword.getText().toString();
                Intent intent = getIntent();
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initData() {

    }

    private void initView() {
        edInputUsername = findViewById(R.id.et_input_username);
        edInputPassword = findViewById(R.id.et_input_password);
        btnSaveData = findViewById(R.id.btn_save_data);

        Toolbar toolbar = findViewById(R.id.tb_back_data);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}