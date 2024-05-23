package com.example.demo.main.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;
import com.example.demo.main.first.activity.auxiliary.MyParcelableObject;
import com.example.demo.main.first.activity.auxiliary.MySerializableObject;

public class IntentBundleDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 1;
    private byte byteValue;
    private short shortValue;
    private int intValue;
    private long longValue;
    private float floatValue;
    private double doubleValue;
    private char charValue;
    private boolean booleanValue;
    private String stringValue;
    private int[] intArrayValue;
    private MySerializableObject serializableValue;
    private MyParcelableObject parcelableValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_bundle_detail);

        initData();
        initView();
    }

    private void initView() {
        Button btnExplicitIntent = findViewById(R.id.btn_intent_explicit);
        btnExplicitIntent.setOnClickListener(this);
        Button btnImplicitIntent = findViewById(R.id.btn_intent_implicit);
        btnImplicitIntent.setOnClickListener(this);
        Button btnBackData = findViewById(R.id.btn_back_data);
        btnBackData.setOnClickListener(this);
        Button btnBundle = findViewById(R.id.btn_bundle);
        btnBundle.setOnClickListener(this);
        Button btnProblem = findViewById(R.id.btn_problem);
        btnProblem.setOnClickListener(this);
        Button btnImplicitIntentOpenPhone = findViewById(R.id.btn_implicit_intent_open_phone);
        btnImplicitIntentOpenPhone.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.tb_intent_bundle_detail);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        byteValue = 1;
        shortValue = 2;
        intValue = 3;
        longValue = 4L;
        floatValue = 5.0f;
        doubleValue = 6.0;
        charValue = '7';
        booleanValue = true;
        stringValue = "String";
        intArrayValue = new int[]{1, 2, 3, 4, 5, 6};
        serializableValue = new MySerializableObject(1, "zs");
        parcelableValue = new MyParcelableObject(2, "ls");
    }

    @Override
    public void onClick(View v) {
        //显示Intent
        if (v.getId() == R.id.btn_intent_explicit) {
            Intent intent = new Intent(this, ExplicitIntentActivity.class);
            intent.putExtra("byteKey", byteValue);
            intent.putExtra("shortKey", shortValue);
            intent.putExtra("intKey", intValue);
            intent.putExtra("longKey", longValue);
            intent.putExtra("floatKey", floatValue);
            intent.putExtra("doubleKey", doubleValue);
            intent.putExtra("charKey", charValue);
            intent.putExtra("booleanKey", booleanValue);
            intent.putExtra("stringKey", stringValue);
            intent.putExtra("arrayKey", intArrayValue);
            intent.putExtra("serializableKey", serializableValue);
            intent.putExtra("parcelableKey", parcelableValue);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_intent_implicit) {
            //隐式Intent
            Intent intent = new Intent("com.example.demo.MYACTION");
//            intent.addCategory("com.example.demo.MYCATEGORY");
            intent.putExtra("byteKey", byteValue);
            intent.putExtra("shortKey", shortValue);
            intent.putExtra("intKey", intValue);
            intent.putExtra("longKey", longValue);
            intent.putExtra("floatKey", floatValue);
            intent.putExtra("doubleKey", doubleValue);
            intent.putExtra("charKey", charValue);
            intent.putExtra("booleanKey", booleanValue);
            intent.putExtra("stringKey", stringValue);
            intent.putExtra("arrayKey", intArrayValue);
            intent.putExtra("serializableKey", serializableValue);
            intent.putExtra("parcelableKey", parcelableValue);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_back_data) {
            //带有返回值的Intent
            Intent intent = new Intent(this, BackDataActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (v.getId() == R.id.btn_bundle) {
            //Bundle传值
            Intent intent = new Intent(this, BundleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putByte("byteKey", byteValue);
            bundle.putShort("shortKey", shortValue);
            bundle.putInt("intKey", intValue);
            bundle.putLong("longKey", longValue);
            bundle.putFloat("floatKey", floatValue);
            bundle.putDouble("doubleKey", doubleValue);
            bundle.putChar("charKey", charValue);
            bundle.putBoolean("booleanKey", booleanValue);
            bundle.putString("stringKey", stringValue);
            bundle.putIntArray("arrayKey", intArrayValue);
            bundle.putSerializable("serializableKey", serializableValue);
            bundle.putParcelable("parcelableKey", parcelableValue);
            intent.putExtra("myBundle", bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_problem) {
            //数据传递的临界值
            Intent intent = new Intent(this, DataProblemActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.btn_implicit_intent_open_phone){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");
            Toast.makeText(this, "username=" + username + "\npassword=" + password, Toast.LENGTH_SHORT).show();
        }
    }
}