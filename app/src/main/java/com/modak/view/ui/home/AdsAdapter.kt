package com.modak.view.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.modak.view.ui.fragments.BlankFragment
import com.modak.view.ui.home.ads.Ads1Fragment
import com.modak.view.ui.home.ads.Ads2Fragment


class AdsAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Ads1Fragment()
                true
            }
            1 -> {
                return Ads2Fragment()
                true
            }
            2 -> {
                return Ads1Fragment()
                true
            }
            3 -> {
                return Ads2Fragment()
                true
            }
            4 -> {
                return Ads1Fragment()
                true
            }
            5 -> {
                return Ads2Fragment()
                true
            }

            else -> {
                return BlankFragment()
            }
        }
        true
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }


}
