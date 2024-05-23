package com.example.demo.main.first.ui.fourth_activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.R

class ListViewActivity : AppCompatActivity() {
    private val fruitList = listOf(
        "Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
        "Pineapple", "Strawberry", "Cherry", "Mango"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_view)

        val listView: ListView = findViewById(R.id.lv_test)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fruitList)
        listView.adapter = adapter
    }
}