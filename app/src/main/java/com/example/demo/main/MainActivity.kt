package com.example.demo.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.demo.R
import com.example.demo.utils.adapter.MyFragmentAdapter
import com.example.demo.login.OnFragmentInteractionListener
import com.example.demo.main.first.FirstFragment
import com.example.demo.main.fourth.FourthFragment
import com.example.demo.main.second.SecondFragment
import com.example.demo.main.third.ThirdFragment
import com.example.demo.utils.MySQLiteHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),
    OnFragmentInteractionListener {

    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var toolbar: Toolbar

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //设置viewpager2和fragment
        val viewPager2: ViewPager2 = findViewById(R.id.viewpager2)
        val fragmentList =
            listOf(FirstFragment(), SecondFragment(), ThirdFragment(), FourthFragment())
        val adapter = MyFragmentAdapter(this, fragmentList)
        viewPager2.adapter = adapter


        //为BottomNavigationView设置菜单项点击事件的监听器 点击按钮页面切换
        val buttom_navigation_view: BottomNavigationView = findViewById(R.id.buttom_navigation_view)
        buttom_navigation_view.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first_item -> {
                    viewPager2.setCurrentItem(0, true)
                    true
                }

                R.id.second_item -> {
                    viewPager2.setCurrentItem(1, true)
                    true
                }

                R.id.third_item -> {
                    viewPager2.setCurrentItem(2, true)
                    true
                }

                R.id.fourth_item -> {
                    viewPager2.setCurrentItem(3, true)
                    true
                }

                else -> {
                    false
                }
            }
        }

        //页面切换 下面的按钮也切换
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //更新BottomNavigationView的选中项
                val menuItem = buttom_navigation_view.menu.getItem(position)
                menuItem.isChecked = true
            }
        })

        //绑定侧边栏
        drawerLayout = findViewById(R.id.dly_main)
        toolbar = findViewById(R.id.tb_main_activity)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        //使用ActionBarDrawerToggle 都是DrawerLayout用来控制ToolBar的显示
        drawerLayout.addDrawerListener(toggle);
        //同步状态
        toggle.syncState();


        //侧边栏点击菜单也能完成跳转
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first_item -> {
                    viewPager2.setCurrentItem(0, true)
                    drawerLayout.closeDrawer(GravityCompat.START) //跳转后关闭侧边栏
                    true
                }

                R.id.second_item -> {
                    viewPager2.setCurrentItem(1, true)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.third_item -> {
                    viewPager2.setCurrentItem(2, true)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.fourth_item -> {
                    viewPager2.setCurrentItem(3, true)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                else -> {
                    false
                }
            }
        }

        //拿一下登录用户的数据
        val id = intent.getIntExtra("userId", 0)
        val helper = MySQLiteHelper(this, "Login.db", null, 1)
        val db = helper.readableDatabase
        val sql = "select username, email from User where id = ?"
        val query = db.rawQuery(sql, arrayOf(id.toString()))
        if (query.moveToFirst()) {
            val username = query.getString(query.getColumnIndex("username"))
            val email = query.getString(query.getColumnIndex("email"))
            val navigationView = findViewById<NavigationView>(R.id.nav_view)
            val headerView = navigationView.getHeaderView(0)
            val tvUsername = headerView.findViewById<TextView>(R.id.tv_username)
            val tvEmail = headerView.findViewById<TextView>(R.id.tv_email)
            tvUsername.text = username
            tvEmail.text = email
        }

        
    }


    override fun onFragmentTitleChanged(title: String?) {
        toolbar.title = title
    }
}