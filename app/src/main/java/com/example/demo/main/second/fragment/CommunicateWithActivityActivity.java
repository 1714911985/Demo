package com.example.demo.main.second.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo.R;
import com.example.demo.main.second.fragment.auxiliary.ICallBack;
import com.example.demo.main.second.fragment.auxiliary.UtilFragment;

public class CommunicateWithActivityActivity extends AppCompatActivity implements ICallBack {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate_with_activity);

        initData();
        initView();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        UtilFragment fragment = new UtilFragment();

        Bundle bundle = new Bundle();
        bundle.putString("msg", "这是Activity向Fragment发送的消息");
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fm_util, fragment);
        fragmentTransaction.commit();

    }

    private void initView() {

    }

    private void initData() {

    }

    @Override
    public void sendMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}