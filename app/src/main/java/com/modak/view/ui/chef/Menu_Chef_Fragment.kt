package com.modak.view.ui.chef

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.modak.R
import com.modak.view.ui.menu.BreakfastActivity
import com.modak.view.ui.menu.DinnerActivity
import com.modak.view.ui.menu.LunchActivity
import kotlinx.android.synthetic.main.fragment_menu__chef_.*


class Menu_Chef_Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu__chef_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lunch()
        breakfast()
        dinner()

    }
    private fun breakfast() {
        img_menu1.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,BreakfastActivity::class.java)
            activity?.startActivity(intent)
        })
        txt_menu1.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,BreakfastActivity::class.java)
            activity?.startActivity(intent)
        })
    }

    private fun lunch() {
        img_menu2.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,LunchActivity::class.java)
            activity?.startActivity(intent)
        })
        txt_menu2.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,LunchActivity::class.java)
            activity?.startActivity(intent)
        })
    }
    private fun dinner() {
        img_menu3.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,DinnerActivity::class.java)
            activity?.startActivity(intent)
        })
        txt_menu3.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,DinnerActivity::class.java)
            activity?.startActivity(intent)
        })

    }
}