package com.modak.view.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.modak.view.ui.chef.*
import com.modak.view.ui.fragments.BlankFragment


class TabAdapter_chef(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Chef_Info_Fragment()
                true

            }
            1 -> {
                return Review_chef_Fragment()
                true
            }
            2 -> {
                return Share_Chef_Fragment()
                true
            }
            3-> {
                return Rate_Chef_Fragment()
                true
            }
            4 -> {
                return Menu_Chef_Fragment()
                true
            }

            else -> {
                return BlankFragment()
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }

}