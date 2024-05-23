package com.example.demo.main.second

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
import com.example.demo.main.second.androidapplicationresource.ApplicationResourcesActivity
import com.example.demo.main.second.animation.AnimationActivity
import com.example.demo.main.second.broadcast.BroadcastReceiverActivity
import com.example.demo.main.second.broadcast.CreateBroadcastReceiverActivity
import com.example.demo.main.second.fragment.CommunicateWithActivityActivity
import com.example.demo.main.second.fragment.FragmentLifeActivity
import com.example.demo.main.second.fragment.FragmentUsageActivity
import com.example.demo.main.second.fragment.WhatIsFragmentActivity

class SecondFragment : Fragment() , View.OnClickListener{
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
        return inflater.inflate(R.layout.fragment_second, container)
    }

    override fun onResume() {
        super.onResume()
        updateToolbarTitle("第二阶段")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerButton(R.id.btn_application_resources, view)
        registerButton(R.id.btn_animation, view)
        registerButton(R.id.btn_what_is_fragment, view)
        registerButton(R.id.btn_fragment_usage, view)
        registerButton(R.id.btn_fragment_life, view)
        registerButton(R.id.btn_communicate_with_activity, view)
        registerButton(R.id.btn_broadcast_receiver, view)

        registerButton(R.id.btn_create_broadcast_receiver, view)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_application_resources -> {
                val intent = Intent(context, ApplicationResourcesActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_animation -> {
                val intent = Intent(context, AnimationActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_what_is_fragment -> {
                val intent = Intent(context, WhatIsFragmentActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_fragment_usage -> {
                val intent = Intent(context, FragmentUsageActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_fragment_life -> {
                val intent = Intent(context, FragmentLifeActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_communicate_with_activity -> {
                val intent = Intent(context, CommunicateWithActivityActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_broadcast_receiver -> {
                val intent = Intent(context, BroadcastReceiverActivity::class.java)
                startActivity(intent)
            }


            R.id.btn_create_broadcast_receiver -> {
                val intent = Intent(context, CreateBroadcastReceiverActivity::class.java)
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