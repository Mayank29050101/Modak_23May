package com.modak.view.ui.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.modak.R
import com.modak.view.BasketActivity
import com.modak.view.ReviewOrderActivity
import kotlinx.android.synthetic.main.fragment_takeout.*

class TakeoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_takeout, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        linear_takeout.visibility=View.GONE
        btn_order_t.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity, ReviewOrderActivity::class.java)
            activity?.startActivity(intent)
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.blink_button)
            btn_order_t.startAnimation(animation)
        })
        viewcart.setOnClickListener(View.OnClickListener {
            val intent=Intent(activity,BasketActivity::class.java)
            activity?.startActivity(intent)
            viewcart.setTextColor(resources.getColor(R.color.yellow))
            viewcart.setBackgroundColor(resources.getColor(R.color.red))

        })
        choose1.setOnClickListener(View.OnClickListener {
            choose1.isChecked=true
            choose2.isChecked=false
            choose3.isChecked=false
            choose4.isChecked=false
        })
        choose2.setOnClickListener(View.OnClickListener {
            choose1.isChecked=false
            choose2.isChecked=true
            choose3.isChecked=false
            choose4.isChecked=false
        })
        choose3.setOnClickListener(View.OnClickListener {
            choose1.isChecked=false
            choose2.isChecked=false
            choose3.isChecked=true
            choose4.isChecked=false
        })
        choose4.setOnClickListener(View.OnClickListener {
            choose1.isChecked=false
            choose2.isChecked=false
            choose3.isChecked=false
            choose4.isChecked=true
        })
        checkbox_dinein.setOnClickListener(View.OnClickListener {
            checkbox_dinein.isChecked=true
            checkbox_takeout.isChecked=false
            checkbox_delivery.isChecked=false
            linear_takeout.visibility=View.GONE
        })
        checkbox_takeout.setOnClickListener(View.OnClickListener {
            checkbox_dinein.isChecked=false
            checkbox_takeout.isChecked=true
            linear_takeout.isVisible=true
            if (linear_takeout.isVisible){
                linear_takeout.visibility=View.VISIBLE
                checkbox_add_t1.setOnClickListener(View.OnClickListener {
                    checkbox_add_t1.isChecked=true
                    checkbox_add_t2.isChecked=false
                })
                checkbox_add_t2.setOnClickListener(View.OnClickListener {
                    checkbox_add_t1.isChecked=false
                    checkbox_add_t2.isChecked=true
                })
            }
            else{
                linear_takeout.visibility=View.GONE
            }
            checkbox_delivery.isChecked=false
        })
        checkbox_delivery.setOnClickListener(View.OnClickListener {
            checkbox_dinein.isChecked=false
            checkbox_takeout.isChecked=false
            checkbox_delivery.isChecked=true
            linear_takeout.visibility=View.GONE
        })
    }
}