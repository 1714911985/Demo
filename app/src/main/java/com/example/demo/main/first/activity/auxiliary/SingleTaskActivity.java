package com.example.demo.main.first.activity.auxiliary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        initData();
        initView();
    }

    private void initView() {
        Button btnStartStandardActivity = findViewById(R.id.btn_start_standard_activity);
        btnStartStandardActivity.setOnClickListener(this);
        Button btnStartSingleTopActivity = findViewById(R.id.btn_start_singleTop_activity);
        btnStartSingleTopActivity.setOnClickListener(this);
        Button btnStartSingleTaskActivity = findViewById(R.id.btn_start_singleTask_activity);
        btnStartSingleTaskActivity.setOnClickListener(this);
        Button btnStartSingleInstanceActivity = findViewById(R.id.btn_start_singleInstance_activity);
        btnStartSingleInstanceActivity.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.tb_single_task);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_standard_activity) {
            Intent intent = new Intent(this, StandardActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_start_singleTop_activity) {
            Intent intent = new Intent(this, SingleTopActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_start_singleTask_activity) {
            Intent intent = new Intent(this, SingleTaskActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_start_singleInstance_activity) {
            Intent intent = new Intent(this, SingleInstanceActivity.class);
            startActivity(intent);
        }

    }
}