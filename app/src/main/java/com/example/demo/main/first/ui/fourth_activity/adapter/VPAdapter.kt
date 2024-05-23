package com.example.demo.main.first.ui.fourth_activity.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class VPAdapter(val viewList: List<View>) : PagerAdapter() {
    override fun getCount(): Int {
        return viewList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(viewList.get(position))
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList.get(position), 0);
        return viewList.get(position)
    }
}