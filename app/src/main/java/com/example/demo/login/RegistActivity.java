package com.example.demo.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.utils.MySQLiteHelper;
import com.example.demo.R;

import java.util.Objects;

public class RegistActivity extends AppCompatActivity {
    private Button btnConfirm;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etEmail;
    private MySQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initData();
        initView();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断注册是否成功
                if (judgeRegist()) {
                    Intent intent = getIntent();
                    intent.putExtra("responseData", "注册成功");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    //判断是否注册成功
    private boolean judgeRegist() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String email = etEmail.getText().toString();
        if (!Objects.equals(password, confirmPassword)) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        } else if (judgeUsernameIsExistOrNot(username)) {
            Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
            return false;
        } else if (judgeEmailIsExistOrNot(email)) {
            Toast.makeText(this, "该邮箱已被使用", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            save(username, password, email);
            return true;
        }

    }

    private boolean judgeEmailIsExistOrNot(String email) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from User where email = ?";
        Cursor query = db.rawQuery(sql, new String[]{email});
        return query.getCount() != 0;
    }

    //注册成功，存入信息
    private void save(String username, String password, String email) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("email", email);
        db.insert("User", null, values);
        db.close();
    }

    //判断用户名是否已经存在
    private boolean judgeUsernameIsExistOrNot(String username) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from User where username = ?";
        Cursor query = db.rawQuery(sql, new String[]{username});
        return query.getCount() != 0;
    }

    private void initData() {
        helper = new MySQLiteHelper(this, "Login.db", null, 1);
    }

    private void initView() {
        btnConfirm = findViewById(R.id.btn_confirm);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        etEmail = findViewById(R.id.et_email);
    }
}