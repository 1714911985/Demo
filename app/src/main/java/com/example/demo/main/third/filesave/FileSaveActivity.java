package com.example.demo.main.third.filesave;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileSaveActivity extends AppCompatActivity {
    private EditText edFileSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);

        initData();
        initView();
        Toolbar toolbar = findViewById(R.id.tb_file_save);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edFileSave.setText(loadDataByFile());
    }

    private void initView() {
        edFileSave = findViewById(R.id.ed_file_save);
    }

    private void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataByFile(edFileSave.getText().toString());
    }

    private void saveDataByFile(String inputText) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("data", Context.MODE_PRIVATE)))) {
            writer.write(inputText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadDataByFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("data")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}