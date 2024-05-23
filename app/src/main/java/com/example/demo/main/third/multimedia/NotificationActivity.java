package com.example.demo.main.third.multimedia;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;
import com.example.demo.main.third.multimedia.auxiliary.AuxiliaryNotificationActivity;

import java.util.Arrays;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "NotificationActivity";
    private Context context;
    private Button btnOrdinaryNotificaiton;
    private Button btnJumpNotification;
    private Button btnBigTextNotification;
    private Button btnImageNotification;
    private Button btnImportantNotification;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        initData();
        Toolbar toolbar = findViewById(R.id.tb_notification);
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
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ordinaryChannel = new NotificationChannel("ordinary", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel jumpChannel = new NotificationChannel("jump", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel bigTextChannel = new NotificationChannel("bigText", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel imageChannel = new NotificationChannel("image", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel importantChannel = new NotificationChannel("important", "Notification", NotificationManager.IMPORTANCE_HIGH);

            manager.createNotificationChannels(Arrays.asList(ordinaryChannel, jumpChannel, bigTextChannel, imageChannel, importantChannel));
        }
    }

    private void initView() {
        btnOrdinaryNotificaiton = findViewById(R.id.btn_ordinary_notification);
        btnOrdinaryNotificaiton.setOnClickListener(this);
        btnJumpNotification = findViewById(R.id.btn_jump_notification);
        btnJumpNotification.setOnClickListener(this);
        btnBigTextNotification = findViewById(R.id.btn_big_text_notification);
        btnBigTextNotification.setOnClickListener(this);
        btnImageNotification = findViewById(R.id.btn_image_notification);
        btnImageNotification.setOnClickListener(this);
        btnImportantNotification = findViewById(R.id.btn_important_notification);
        btnImportantNotification.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ordinary_notification) {
            Notification notification = new NotificationCompat.Builder(context, "ordinary")
                    .setContentTitle("Ordinary Notification")
                    .setContentText("这是一条普通通知")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build();
            manager.notify(1, notification);
            Log.e(TAG, "走了");
        } else if (v.getId() == R.id.btn_jump_notification) {
            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, AuxiliaryNotificationActivity.class), PendingIntent.FLAG_IMMUTABLE);
            Notification notification = new NotificationCompat.Builder(context, "jump")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Jump Notification")
                    .setContentText("这是一条点击后可以跳转的通知")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();
            manager.notify(2, notification);
        } else if (v.getId() == R.id.btn_big_text_notification) {
            Notification notification = new NotificationCompat.Builder(context, "bigText")
                    .setContentTitle("BigText Notification")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build\n" +
                            "        notifications, send and sync  data, and use voice actions. Get the official\n" +
                            "        Android IDE and developer tools to build apps for Android."))
                    .build();
            manager.notify(3, notification);
        } else if (v.getId() == R.id.btn_image_notification) {
            Notification notification = new NotificationCompat.Builder(context, "image")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.img1)))
                    .setContentTitle("Image Notification")
                    .build();
            manager.notify(4, notification);
        } else if (v.getId() == R.id.btn_important_notification) {
            Notification notification = new NotificationCompat.Builder(this, "important")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Important Notification")
                    .setContentText("重要的通知")
                    .build();
            manager.notify(5, notification);
        }

    }
}