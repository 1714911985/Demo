package com.example.demo.main.first.ui.fourth_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.demo.R
import com.example.demo.main.first.ui.fourth_activity.adapter.VPAdapter


class ViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val vp_test: ViewPager = findViewById(R.id.vp_test)
        val inflate2 = LayoutInflater.from(this).inflate(R.layout.fragment_second, null)
        val inflate3 = LayoutInflater.from(this).inflate(R.layout.fragment_third, null)
        val inflate4 = LayoutInflater.from(this).inflate(R.layout.fragment_fourth, null)
        val viewList: MutableList<View> = ArrayList()
        viewList.add(inflate2)
        viewList.add(inflate3)
        viewList.add(inflate4)

        vp_test.adapter = VPAdapter(viewList)
    }
}