package com.example.demo.main.third.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;
import com.example.demo.main.third.service.myservice.MyIntentService;

public class AndroidMultithreadedProgrammingActivity extends AppCompatActivity implements View.OnClickListener {
    private final int updateText = 1;
    private Button btnChangeTextViewText;
    private TextView tvChangedByButton;
    private ProgressDialog progressDialog;
    private Context context;
    private Button btnAsyncTask;
    private Button btnRunnable;
    private Button btnIntentService;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == updateText) {
                tvChangedByButton.setText("被Handler、Looper 和 MessageQueue改变了");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_multithreaded_programming);
        initView();
        initData();

        Toolbar toolbar = findViewById(R.id.tb_android_multithreaded_programming);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initData() {
        context = this;
    }


    private void initView() {
        btnChangeTextViewText = findViewById(R.id.btn_change_textview_text);
        btnChangeTextViewText.setOnClickListener(this);
        tvChangedByButton = findViewById(R.id.tv_changed_by_button);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        btnAsyncTask = findViewById(R.id.btn_async_task);
        btnAsyncTask.setOnClickListener(this);
        btnRunnable = findViewById(R.id.btn_runnable);
        btnRunnable.setOnClickListener(this);
        btnIntentService = findViewById(R.id.btn_intent_service);
        btnIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_change_textview_text) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = updateText;
                    handler.sendMessage(msg);
                }
            }).start();
        } else if (v.getId() == R.id.btn_async_task) {
            new DownLoadTask(context, progressDialog).execute();
        } else if (v.getId() == R.id.btn_runnable) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.e("Thread&Runnable当前线程名:", Thread.currentThread().getName());
                }
            }).start();
        } else if (v.getId() == R.id.btn_intent_service) {
            startService(new Intent(context, MyIntentService.class));
        }
    }

    //AsyncTask模拟下载任务
    class DownLoadTask extends AsyncTask<Void, Integer, Boolean> {
        private Context context;
        private ProgressDialog progressDialog;

        public DownLoadTask(Context context, ProgressDialog progressDialog) {
            this.context = context;
            this.progressDialog = progressDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                int downloadPercent = doDownload();
                while (true) {
                    downloadPercent++;
                    publishProgress(downloadPercent);
                    Thread.sleep(70);
                    if (downloadPercent >= 100) {
                        break;
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private int doDownload() {
            return 0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setMessage("下载  " + values[0] + "  %");

        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.dismiss();
            if (aBoolean) {
                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "下载失败，请重新下载", Toast.LENGTH_SHORT).show();
            }
        }
    }
}