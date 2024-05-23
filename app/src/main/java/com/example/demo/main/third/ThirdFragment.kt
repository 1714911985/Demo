package com.example.demo.main.third

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.demo.R
import com.example.demo.login.OnFragmentInteractionListener
import com.example.demo.main.third.filesave.DataStoreActivity
import com.example.demo.main.third.filesave.FileSaveActivity
import com.example.demo.main.third.filesave.MMKVActivity
import com.example.demo.main.third.filesave.SQLiteActivity
import com.example.demo.main.third.filesave.SharedPreferenceActivity
import com.example.demo.main.third.multimedia.CameraActivity
import com.example.demo.main.third.multimedia.MediaPlayActivity
import com.example.demo.main.third.multimedia.NotificationActivity
import com.example.demo.main.third.multimedia.VideoViewActivity
import com.example.demo.main.third.service.AndroidMultithreadedProgrammingActivity
import com.example.demo.main.third.service.ServiceLifeActivity
import com.example.demo.main.third.service.ServiceStartAndStopActivity
import com.example.demo.main.third.service.WhatIsServiceActivity

class ThirdFragment : Fragment(), View.OnClickListener {
    private var mListener: OnFragmentInteractionListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onResume() {
        super.onResume()
        updateToolbarTitle("第三阶段")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton(R.id.btn_file_save, view)
        registerButton(R.id.btn_shared_preference, view)
        registerButton(R.id.btn_sqlite, view)
        registerButton(R.id.btn_mmkv, view)
        registerButton(R.id.btn_datastore, view)
        registerButton(R.id.btn_what_is_service, view)
        registerButton(R.id.btn_android_multithreaded_programming, view)
        registerButton(R.id.btn_service_start_and_stop, view)
        registerButton(R.id.btn_service_life, view)
        registerButton(R.id.btn_notification, view)
        registerButton(R.id.btn_camera, view)
        registerButton(R.id.btn_media_play, view)
        registerButton(R.id.btn_video_view, view)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_file_save -> {
                val intent = Intent(context, FileSaveActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_shared_preference -> {
                val intent = Intent(context, SharedPreferenceActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_sqlite -> {
                val intent = Intent(context, SQLiteActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_mmkv -> {
                val intent = Intent(context, MMKVActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_datastore -> {
                val intent = Intent(context, DataStoreActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_what_is_service -> {
                val intent = Intent(context, WhatIsServiceActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_android_multithreaded_programming -> {
                val intent = Intent(context, AndroidMultithreadedProgrammingActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_service_start_and_stop -> {
                startActivity(Intent(context, ServiceStartAndStopActivity::class.java))
            }

            R.id.btn_service_life -> {
                startActivity(Intent(context, ServiceLifeActivity::class.java))
            }

            R.id.btn_notification -> {
                startActivity(Intent(context, NotificationActivity::class.java))
            }

            R.id.btn_camera -> {
                startActivity(Intent(context, CameraActivity::class.java))
            }

            R.id.btn_media_play -> {
                startActivity(Intent(context, MediaPlayActivity::class.java))
            }

            R.id.btn_video_view -> {
                startActivity(Intent(context, VideoViewActivity::class.java))
            }
        }
    }


    fun updateToolbarTitle(title: String?) {
        if (mListener != null) {
            mListener!!.onFragmentTitleChanged(title)
        }
    }

    private fun registerButton(buttonId: Int, view: View) {
        val button: Button = view.findViewById(buttonId)
        button.setOnClickListener(this)
    }


}
