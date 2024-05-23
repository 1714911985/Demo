package com.example.demo.login;

import android.content.ContentValues;
import android.content.Intent;
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

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {
    private Button btnConfirm;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private MySQLiteHelper helper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Log.e("ChangePasswordActivity","onCreate");
        initData();
        initView();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judgePasswordIsConsistent()) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String sql = "update User set password = ? where id = ?";
                    db.execSQL(sql,new Object[]{etPassword.getText().toString(),id});
                    db.close();


                    Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                    intent.putExtra("msg","密码修改成功");
                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        helper = new MySQLiteHelper(this, "Login.db", null, 1);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
    }

    private void initView() {
        btnConfirm = findViewById(R.id.btn_confirm);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
    }

    private boolean judgePasswordIsConsistent() {
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if (!Objects.equals(password, confirmPassword)) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}