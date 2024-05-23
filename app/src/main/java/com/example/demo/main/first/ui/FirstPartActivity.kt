package com.example.demo.main.first.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.demo.R

class FirstPartActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_part)

        val toolbar = findViewById<Toolbar>(R.id.tb_first_part)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }

        registButton(R.id.btn_this_is_button)
        val getCheck: Button = findViewById(R.id.getChecked)
        getCheck.setOnClickListener {
            val checkedRadioButton: RadioButton =
                findViewById(findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId)
            Toast.makeText(this, checkedRadioButton.text, Toast.LENGTH_SHORT).show()
        }


        //显示选中的checkbox
        val getChecked2: Button = findViewById(R.id.getChecked2)
        getChecked2.setOnClickListener {
            //存储所有多选
            val checkBoxList =
                listOf(R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4)
            //存储选中的多选内容
            val checkedList: MutableList<String> = mutableListOf()
            //遍历拿到被选中的内容
            for (checkBoxId in checkBoxList) {
                val checkBox: CheckBox = findViewById(checkBoxId)
                if (checkBox.isChecked) {
                    checkedList.add(checkBox.text.toString())
                }
            }
            if (checkedList.size != 0) {
                Toast.makeText(this, checkedList.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "没有选项被选中", Toast.LENGTH_SHORT).show()
            }
        }

        //ToggleButton是否禁用的判断
        judgeToggleButtonIsEnabled()

        //switch打开轨道为绿色，关闭轨道为灰色
//        changeSwitchTrackTint()
    }

    /**
     * 注册按钮并设置监听器
     */
    public fun registButton(buttonId: Int) {
        val button: Button = findViewById(buttonId)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_this_is_button -> Toast.makeText(this, "This is Button", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //switch打开轨道为绿色，关闭轨道为灰色
    private fun changeSwitchTrackTint() {
        val switch: Switch = findViewById(R.id.switch1)

        // 创建ColorStateList
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked), // 选中状态
            intArrayOf(-android.R.attr.state_checked) // 未选中状态
        )

        val colors = intArrayOf(
            Color.GREEN, // 选中时的颜色（绿色）
            Color.GRAY // 未选中时的颜色（灰色）
        )

        // 创建ColorStateList并应用到Switch的轨道
        val trackTintList = ColorStateList(states, colors)
        switch.trackTintList = trackTintList
    }

    //ToggleButton是否禁用的判断
    private fun judgeToggleButtonIsEnabled() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)
        radioGroup.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.male -> {
                    toggleButton.isEnabled = true
                }

                else -> {
                    toggleButton.isEnabled = false
                }
            }
        }
    }
}