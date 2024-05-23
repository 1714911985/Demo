package com.example.demo.main.first.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.demo.R
import com.example.demo.main.first.ui.fourth_activity.GridViewActivity
import com.example.demo.main.first.ui.fourth_activity.ListViewActivity
import com.example.demo.main.first.ui.fourth_activity.RecycleViewActivity
import com.example.demo.main.first.ui.fourth_activity.ViewPagerActivity
import com.example.demo.utils.myview.MyDialog
import com.google.android.material.bottomsheet.BottomSheetDialog


class FourthPartActivity : AppCompatActivity() {
    private lateinit var myDialog: MyDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth_part)

        val toolbar = findViewById<Toolbar>(R.id.tb_fourth_part)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }

        //AlertDialog
        val alertDialog: Button = findViewById(R.id.alertDialog)
        alertDialog.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("AlertDialog")
                setMessage("This is Message...")
                setCancelable(true)
                setPositiveButton("确定") { _, _ -> }
                setNegativeButton("取消") { _, _ -> }
                show()
            }
        }

        //  BottomSheetDialog
        val bottomSheetDialogBtn: Button = findViewById(R.id.bottomDialog)
        bottomSheetDialogBtn.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            if (bottomSheetDialog != null) {
                bottomSheetDialog.setContentView(R.layout.activity_dialog_bottom_sheet)
                bottomSheetDialog.show()
            }
        }


        //PopuWindow
        val popupWindowBtn: Button = findViewById(R.id.popupWindow)
        popupWindowBtn.setOnClickListener {
            val contentView: View =
                LayoutInflater.from(this).inflate(R.layout.activity_dialog_bottom_sheet, null)
            val popupWindow = PopupWindow(
                contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            popupWindow.showAsDropDown(popupWindowBtn, -100, 10, Gravity.CENTER_HORIZONTAL)
        }

        //ListView
        val listView: Button = findViewById(R.id.listView)
        listView.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            startActivity(intent)
        }

        //GridView
        val gridView: Button = findViewById(R.id.gridView)
        gridView.setOnClickListener {
            val intent = Intent(this, GridViewActivity::class.java)
            startActivity(intent)
        }

        //RecycleView
        val recycleView: Button = findViewById(R.id.recycleView)
        recycleView.setOnClickListener {
            val intent = Intent(this, RecycleViewActivity::class.java)
            startActivity(intent)
        }


        //Viewpager
        val viewPager: Button = findViewById(R.id.viewPager)
        viewPager.setOnClickListener {
            val intent = Intent(this, ViewPagerActivity::class.java)
            startActivity(intent)
        }

        //自定义toast
        val toastButton = findViewById<Button>(R.id.btn_toast)
        toastButton.setOnClickListener {
            Toast.makeText(this, "这是普通的Toast", Toast.LENGTH_SHORT).show()

            val toast: Toast = Toast.makeText(this, "这是自定义位置的Toast", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

            val view = layoutInflater.inflate(R.layout.toast_custom_blue, null)
            //val tvToast = findViewById<TextView>(R.id.tv_toast)

            val toast1 = Toast(this)
            toast1.setGravity(Gravity.TOP, 0, 0);
            toast1.setDuration(Toast.LENGTH_SHORT);
            toast1.setView(view);
            toast1.show();
        }

        val btnDialog: Button = findViewById(R.id.btn_dialog)
        btnDialog.setOnClickListener {
            myDialog = MyDialog(this@FourthPartActivity)
            myDialog.setTitle("提示")
            myDialog.setMessage("确定退出应用?")
            myDialog.setYesOnclickListener("确定", object : MyDialog.onYesOnclickListener {
                override fun onYesClick() {
                    Toast.makeText(this@FourthPartActivity, "点击了--确定--按钮", Toast.LENGTH_LONG)
                        .show()
                    myDialog.dismiss()
                }
            })
            myDialog.setNoOnclickListener("取消", object : MyDialog.onNoOnclickListener {
                override fun onNoClick() {
                    Toast.makeText(this@FourthPartActivity, "点击了--取消--按钮", Toast.LENGTH_LONG)
                        .show()
                    myDialog.dismiss()
                }
            })
            myDialog.show()

        }
    }
}