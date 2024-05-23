package com.example.demo.main.second.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo.R;
import com.example.demo.main.second.fragment.auxiliary.BottomFragment;

public class FragmentUsageActivity extends AppCompatActivity {
    private Button btnTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_usage);
        initData();
        initView();

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new BottomFragment());
            }
        });
    }

    private void initView() {
        btnTop = findViewById(R.id.btn_top);
    }

    private void initData() {
    }

    //将下方的FrameLayout替换为Fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_bottom, fragment);
        fragmentTransaction.addToBackStack(null);//添加返回栈
        fragmentTransaction.commit();
    }
}