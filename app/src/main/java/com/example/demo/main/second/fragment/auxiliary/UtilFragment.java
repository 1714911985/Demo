package com.example.demo.main.second.fragment.auxiliary;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.R;

public class UtilFragment extends Fragment {
    private final static String TAG = "UtilFragment";
    private String msg;
    private ICallBack iCallBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ICallBack){
            iCallBack = (ICallBack) context;
        }else {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            msg = bundle.getString("msg","没收到");

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_util, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.btn_util_fragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, msg);
                Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        Button button1 = view.findViewById(R.id.btn_send_msg);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.sendMessage("这是Fragment向Activity发送的消息");
            }
        });
    }
}