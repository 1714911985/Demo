package com.example.demo.main.first.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.demo.R

class SecondPartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_part)

        val toolbar = findViewById<Toolbar>(R.id.tb_second_part)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }

        //设置进度条进度
        val button: Button = findViewById(R.id.button1)
        button.setOnClickListener {
            val progressBar: ProgressBar = findViewById(R.id.progressBar2)
            if (progressBar.progress < 100) {
                progressBar.progress += 10
            } else {
                progressBar.progress = 0
            }
        }

        //seekbar拖动结束后显示进度
        val seekBar: SeekBar = findViewById(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //开始拖动的实现
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //停止拖动的实现
                if (seekBar != null) {
                    val showSeekBarProgress: TextView = findViewById(R.id.showSeekBarProgress)
                    showSeekBarProgress.text = seekBar.progress.toString()
//                    Toast.makeText(context, seekBar.progress.toString(), Toast.LENGTH_SHORT)
//                        .show()
                }
            }

        })

        //ratingbar点击后显示分数
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            val showRatingBarProgress: TextView = findViewById(R.id.showRatingBarProgress)
            showRatingBarProgress.text = rating.toString()

        }
    }
}