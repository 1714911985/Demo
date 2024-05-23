package com.example.demo.main.first

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.demo.login.OnFragmentInteractionListener
import com.example.demo.R
import com.example.demo.main.first.activity.ActivityLifeActivity
import com.example.demo.main.first.activity.HowToCreateActivityActivity
import com.example.demo.main.first.activity.IntentBundleDetailActivity
import com.example.demo.main.first.activity.LaunchModeActivity
import com.example.demo.main.first.ui.MyViewActivity
import com.example.demo.main.first.activity.WhatIsActivityActivity
import com.example.demo.main.first.ui.FirstPartActivity
import com.example.demo.main.first.ui.FourthPartActivity
import com.example.demo.main.first.ui.SecondPartActivity
import com.example.demo.main.first.ui.ThirdPartActivity


class FirstFragment : Fragment(), View.OnClickListener {
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

    override fun onResume() {
        super.onResume()
        updateToolbarTitle("第一阶段")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerButton(R.id.btn_what_is_activity, view)
        registerButton(R.id.btn_how_to_create_activity, view)
        registerButton(R.id.btn_intent_bundle, view)
        registerButton(R.id.btn_activity_life, view)
        registerButton(R.id.btn_launch_mode, view)
        registerButton(R.id.btn_first_part, view)
        registerButton(R.id.btn_second_part, view)
        registerButton(R.id.btn_third_part, view)
        registerButton(R.id.btn_fourth_part, view)
        registerButton(R.id.btn_my_view, view)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_what_is_activity -> {
                val intent = Intent(context, WhatIsActivityActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_how_to_create_activity -> {
                val intent = Intent(context, HowToCreateActivityActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_intent_bundle -> {
                val intent = Intent(context, IntentBundleDetailActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_activity_life -> {
                val intent = Intent(context, ActivityLifeActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_launch_mode -> {
                val intent = Intent(context, LaunchModeActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_first_part -> {
                val intent = Intent(context, FirstPartActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_second_part -> {
                val intent = Intent(context, SecondPartActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_third_part -> {
                val intent = Intent(context, ThirdPartActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_fourth_part -> {
                val intent = Intent(context, FourthPartActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_my_view -> {
                val intent = Intent(context, MyViewActivity::class.java)
                startActivity(intent)
            }


        }
    }


    private fun registerButton(buttonId: Int, view: View) {
        val button: Button = view.findViewById(buttonId)
        button.setOnClickListener(this)
    }

    fun updateToolbarTitle(title: String?) {
        if (mListener != null) {
            mListener!!.onFragmentTitleChanged(title)
        }
    }

}