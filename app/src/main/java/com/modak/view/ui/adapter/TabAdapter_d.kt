package com.modak.view.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.modak.view.ui.detail.DetailsFragment
import com.modak.view.ui.detail.ReviewsFragment


class TabAdapter_d(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int):Fragment {
        return when (position) {
            0 -> {

                return DetailsFragment()


            }
            1 -> {
                return ReviewsFragment()

            }

            else -> {
                getItem(position)
            }
        }

    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }


}