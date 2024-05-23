package com.example.demo.main.first.ui.fourth_activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.R

class GridViewActivity : AppCompatActivity() {

    private val fruitList = listOf(
        "Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
        "Pineapple", "Strawberry", "Cherry", "Mango", "Apple", "Banana",
        "Orange", "Watermelon", "Pear", "Grape", "Pineapple",
        "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange",
        "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        val gv_test: GridView = findViewById(R.id.gv_test)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fruitList)
        gv_test.adapter = adapter
    }
}