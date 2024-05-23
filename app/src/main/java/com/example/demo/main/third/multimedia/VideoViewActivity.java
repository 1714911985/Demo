package com.example.demo.main.third.multimedia;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;

public class VideoViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btnVideoPlay;
    private Button btnVideoPause;
    private Button btnVideoReplay;
    private VideoView vvVideoView;
    private final static String TAG = "VideoViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        initView();
        initData();
        Toolbar toolbar = findViewById(R.id.tb_video_view);
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
        btnVideoPlay = findViewById(R.id.btn_video_play);
        btnVideoPlay.setOnClickListener(this);
        btnVideoPause = findViewById(R.id.btn_video_pause);
        btnVideoPause.setOnClickListener(this);
        btnVideoReplay = findViewById(R.id.btn_video_replay);
        btnVideoReplay.setOnClickListener(this);
        vvVideoView = findViewById(R.id.vv_video_view);
        Log.e(TAG, "android.resource://" + getPackageName() + "/" + R.raw.video);
        vvVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_video_play) {
            if (!vvVideoView.isPlaying()) {
                vvVideoView.start();
            }
        } else if (v.getId() == R.id.btn_video_pause) {
            if (vvVideoView.isPlaying()) {
                vvVideoView.pause();
            }
        } else if (v.getId() == R.id.btn_video_replay) {
            if (vvVideoView.isPlaying()) {
                vvVideoView.resume();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vvVideoView.suspend();
    }
}