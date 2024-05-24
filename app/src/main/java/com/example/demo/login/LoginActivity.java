package com.example.demo.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.example.demo.main.MainActivity;
import com.example.demo.utils.MySQLiteHelper;
import com.example.demo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final static int REQUEST_CODE = 1;
    private Button btnRegist;
    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;
    private MySQLiteHelper helper;
    private CheckBox cbRememberPassword;
    private Button btnForgetPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("LoginActivity", "onCreate");
        initView();
        initData();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("LoginActivity", "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //记住密码的逻辑
        SharedPreferences preferences = getSharedPreferences("rememberPassword", Context.MODE_PRIVATE);
        boolean isChecked = preferences.getBoolean("isChecked", false);
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        cbRememberPassword.setChecked(isChecked);
        etUsername.setText(username);
        etPassword.setText(password);

        //修改密码成功后的显示
        Intent intent = getIntent();
        if (intent.getStringExtra("msg") != null) {
            Toast.makeText(LoginActivity.this, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        btnRegist = findViewById(R.id.btn_regist);
        btnRegist.setOnClickListener(this);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbRememberPassword = findViewById(R.id.cb_remember_password);
        btnForgetPassword = findViewById(R.id.btn_forgot_password);
        btnForgetPassword.setOnClickListener(this);
    }

    private void initData() {
        helper = new MySQLiteHelper(this, "Login.db", null, 1);
        helper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_regist) {
            //注册
            Intent intent = new Intent(this, RegistActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (v.getId() == R.id.btn_login) {
            //登录
            if (judgeUsernameAndPasswordIsRightOrNot()) {
                SharedPreferences.Editor editor = getSharedPreferences("rememberPassword", Context.MODE_PRIVATE).edit();
                //判断是否勾选了记住密码并存储数据
                if (cbRememberPassword.isChecked()) {
                    //勾选了记住密码
                    editor.putBoolean("isChecked", true);
                    editor.putString("username", etUsername.getText().toString());
                    editor.putString("password", etPassword.getText().toString());
                    editor.apply();
                } else {
                    //没勾选记住密码
                    editor.putBoolean("isChecked", false);
                    editor.putString("username", etUsername.getText().toString());
                    editor.putString("password", "");
                    editor.apply();
                }
                Intent intent = new Intent(this, MainActivity.class);
                //拿用户id
                String username = etUsername.getText().toString();
                int id = getUserId(username);
                intent.putExtra("userId", id);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_forgot_password) {
            Log.e("LoginActivity", "click forgot pwd");
            //忘记密码的逻辑
            Intent intent = new Intent(this, CheckEmailActivity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("Range")
    private int getUserId(String username) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select id from User where username = ?";
        Cursor query = db.rawQuery(sql, new String[]{username});
        if (query.moveToFirst()) {
            return query.getInt(query.getColumnIndex("id"));
        }
        return 0;
    }

    private boolean judgeUsernameAndPasswordIsRightOrNot() {
        String sql = "select * from User where username = ? and password = ?";
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor query = db.rawQuery(sql, new String[]{username, password});
        return query.getCount() != 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "运行了");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String responseData = data.getStringExtra("responseData");
            Toast.makeText(this, responseData, Toast.LENGTH_SHORT).show();
        }
    }

}