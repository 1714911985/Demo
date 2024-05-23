package com.example.demo.main.first.ui.fourth_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.main.first.ui.fourth_activity.adapter.Item
import com.example.demo.main.first.ui.fourth_activity.adapter.MyAdapter


class RecycleViewActivity : AppCompatActivity() {

    private val fruitList = listOf(
        "Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
        "Pineapple", "Strawberry", "Cherry", "Mango"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recycle_view)

        val recyclerView: RecyclerView = findViewById(R.id.rv_test)
        // 创建数据列表
        // 创建数据列表
        val items: MutableList<Item> = ArrayList<Item>()
        for (i in 0..19) {
            items.add(Item("Item $i"))
        }

        // 创建并设置适配器

        // 创建并设置适配器
        val adapter = MyAdapter(items)
        recyclerView.setAdapter(adapter)

        // 设置RecyclerView的布局管理器（这里使用LinearLayoutManager）

        // 设置RecyclerView的布局管理器（这里使用LinearLayoutManager）
        recyclerView.setLayoutManager(LinearLayoutManager(this))
    }
}