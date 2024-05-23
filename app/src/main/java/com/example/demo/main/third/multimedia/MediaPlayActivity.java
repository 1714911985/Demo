package com.example.demo.main.third.multimedia;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class MediaPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btnMusicPlay;
    private Button btnMusicPause;
    private Button btnMusicStop;
    private MediaPlayer mediaPlayer;
    private final static String TAG = "MediaPlayActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_play);

        initView();
        initData();
        initMediaPlayer();
        Toolbar toolbar = findViewById(R.id.tb_media_play);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        btnMusicPlay = findViewById(R.id.btn_music_play);
        btnMusicPlay.setOnClickListener(this);
        btnMusicPause = findViewById(R.id.btn_music_pause);
        btnMusicPause.setOnClickListener(this);
        btnMusicStop = findViewById(R.id.btn_music_stop);
        btnMusicStop.setOnClickListener(this);
    }

    private void initData() {
        context = this;
    }

    private void initMediaPlayer() {
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.music);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_music_play) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                Log.e(TAG, "开始播放音乐");
            }
        } else if (v.getId() == R.id.btn_music_pause) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                Log.e(TAG, "暂停音乐");
            }
        } else if (v.getId() == R.id.btn_music_stop) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
                Log.e(TAG, "停止音乐");
                initMediaPlayer();

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}