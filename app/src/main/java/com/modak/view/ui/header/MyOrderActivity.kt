package com.modak.view.ui.header

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.modak.R
import com.modak.view.ui.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_my_order.*

class MyOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " My Orders"
        tablayout!!.addTab(tablayout!!.newTab().setText("Current Orders"))
        tablayout!!.addTab(tablayout!!.newTab().setText("Past Orders"))
        tablayout!!.tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(this, supportFragmentManager, tablayout!!.tabCount)
        viewpager!!.adapter = adapter

        viewpager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))

        tablayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("Range")
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager!!.currentItem = tab.position


            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}