package com.example.demo.main.fourth

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
import com.example.demo.main.fourth.androidcommunication.ProcessCommunicationActivity
import com.example.demo.main.fourth.androidcommunication.ThreadCommunicationActivity
import com.example.demo.main.fourth.androidcommunication.ThreeWayCommunicationActivity
import com.example.demo.main.fourth.networktechnology.AccessTheNetworkUsingHttpProtocolActivity
import com.example.demo.main.fourth.networktechnology.ParseJsonActivity
import com.example.demo.main.fourth.networktechnology.ParseXmlActivity
import com.example.demo.main.fourth.networktechnology.WebViewActivity

class FourthFragment : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_fourth, container)
    }

    override fun onResume() {
        super.onResume()
        updateToolbarTitle("第四阶段")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerButton(R.id.btn_web_view, view)
        registerButton(R.id.btn_use_http, view)
        registerButton(R.id.btn_parse_xml, view)
        registerButton(R.id.btn_parse_json, view)
        registerButton(R.id.btn_thread, view)
        registerButton(R.id.btn_process, view)
        registerButton(R.id.btn_three_way, view)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_web_view -> {
                startActivity(Intent(context, WebViewActivity::class.java))
            }

            R.id.btn_use_http -> {
                startActivity(
                    Intent(
                        context,
                        AccessTheNetworkUsingHttpProtocolActivity::class.java
                    )
                )
            }

            R.id.btn_parse_xml -> {
                startActivity(Intent(context, ParseXmlActivity::class.java))
            }

            R.id.btn_parse_json -> {
                startActivity(Intent(context, ParseJsonActivity::class.java))
            }

            R.id.btn_thread -> {
                startActivity(Intent(context, ThreadCommunicationActivity::class.java))
            }

            R.id.btn_process -> {
                startActivity(Intent(context, ProcessCommunicationActivity::class.java))
            }

            R.id.btn_three_way -> {
                startActivity(Intent(context, ThreeWayCommunicationActivity::class.java))
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