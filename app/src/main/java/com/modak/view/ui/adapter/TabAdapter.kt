package com.modak.view.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.modak.view.ui.order.CurrentOrderFragment
import com.modak.view.ui.order.PastOrderFragment

class TabAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int):Fragment {
        when (position) {
            0 -> {

                return CurrentOrderFragment()
                true

            }
            1 -> {
                return PastOrderFragment()
                true
            }

            else -> {
                return CurrentOrderFragment()
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }

}