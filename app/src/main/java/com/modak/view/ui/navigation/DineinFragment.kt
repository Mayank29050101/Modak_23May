package com.modak.view.ui.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.modak.R
import kotlinx.android.synthetic.main.fragment_dienin.*


class DineinFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dienin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_table.setOnClickListener(View.OnClickListener {
//            val intent= Intent(activity, MapsActivity::class.java)
//            activity?.startActivity(intent)
            val browser=Uri.parse("http://maps.google.com/maps?")
            val intent=Intent(Intent.ACTION_VIEW,browser)
            startActivity(intent)
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.blink_button)
            btn_table.startAnimation(animation)
        })
    }

}