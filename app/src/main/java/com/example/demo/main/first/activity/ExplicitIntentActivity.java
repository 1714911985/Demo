package com.example.demo.main.first.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;

import java.util.Arrays;

public class ExplicitIntentActivity extends AppCompatActivity {
    private TextView tvByte;
    private TextView tvShort;
    private TextView tvInt;
    private TextView tvLong;
    private TextView tvFloat;
    private TextView tvDouble;
    private TextView tvChar;
    private TextView tvBoolean;
    private TextView tvString;
    private TextView tvArray;
    private TextView tvSerializable;
    private TextView tvParcelable;
    private Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        initData();
        initView();

        tvByte.setText("byte:" + intent.getByteExtra("byteKey", (byte) 0));
        tvShort.setText("short:" + intent.getShortExtra("shortKey", (short) 0));
        tvInt.setText("int:" + intent.getIntExtra("intKey", 0));
        tvLong.setText("long:" + intent.getLongExtra("longKey", 0L));
        tvFloat.setText("float:" + intent.getFloatExtra("floatKey", 0.0f));
        tvDouble.setText("double:" + intent.getDoubleExtra("doubleKey", 0.0));
        tvChar.setText("char:" + intent.getCharExtra("charKey", '0'));
        tvBoolean.setText("boolean:" + intent.getBooleanExtra("booleanKey", false));
        tvString.setText("string:" + intent.getStringExtra("stringKey"));
        tvArray.setText("array:" + Arrays.toString(intent.getIntArrayExtra("arrayKey")));
        tvSerializable.setText("serializable:" + intent.getSerializableExtra("serializableKey"));
        tvParcelable.setText("parcelable:" + intent.getParcelableExtra("parcelableKey"));

    }

    private void initView() {
        tvByte = findViewById(R.id.tv_byte);
        tvShort = findViewById(R.id.tv_short);
        tvInt = findViewById(R.id.tv_int);
        tvLong = findViewById(R.id.tv_long);
        tvFloat = findViewById(R.id.tv_float);
        tvDouble = findViewById(R.id.tv_double);
        tvChar = findViewById(R.id.tv_char);
        tvBoolean = findViewById(R.id.tv_boolean);
        tvString = findViewById(R.id.tv_string);
        tvArray = findViewById(R.id.tv_array);
        tvSerializable = findViewById(R.id.tv_serializable);
        tvParcelable = findViewById(R.id.tv_parcelable);
        Toolbar toolbar = findViewById(R.id.tb_intent_explicit);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        intent = getIntent();
    }
}