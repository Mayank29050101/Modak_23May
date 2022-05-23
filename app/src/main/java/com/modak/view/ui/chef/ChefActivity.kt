package com.modak.view.ui.chef

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.modak.R
import com.modak.view.ui.adapter.TabAdapter_chef
import kotlinx.android.synthetic.main.activity_chef.*

class ChefActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Chef Details"
        tablayout_chef!!.addTab(tablayout_chef!!.newTab().setText("Info").setIcon(R.drawable.ic_baseline_person_24))
        tablayout_chef!!.addTab(tablayout_chef!!.newTab().setText("Reviews").setIcon(R.drawable.icon_review))
        tablayout_chef!!.addTab(tablayout_chef!!.newTab().setText("Share").setIcon(R.drawable.icon_share))
        tablayout_chef!!.addTab(tablayout_chef!!.newTab().setText("Rate").setIcon(R.drawable.ic_baseline_stars_24))
        tablayout_chef!!.addTab(tablayout_chef!!.newTab().setText("Menu").setIcon(R.drawable.icon_menu_chef_detail))
        tablayout_chef!!.tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_FILL

        val adapter = TabAdapter_chef(this, supportFragmentManager, tablayout_chef!!.tabCount)
        viewpager_chef!!.adapter = adapter

        viewpager_chef!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout_chef))

        tablayout_chef!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("Range")
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager_chef!!.currentItem = tab.position


            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}