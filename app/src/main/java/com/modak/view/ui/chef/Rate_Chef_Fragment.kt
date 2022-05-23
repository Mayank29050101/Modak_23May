package com.modak.view.ui.chef

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.modak.R
import kotlinx.android.synthetic.main.activity_rating.*


class Rate_Chef_Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate__chef_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (rBar != null) {
            btn_sr?.setOnClickListener {
                val msg = rBar.rating.toString()
                Toast.makeText(requireContext(),""+msg,Toast.LENGTH_LONG).show()
                Toast.makeText(requireActivity(), "Rating is: "+msg, Toast.LENGTH_SHORT).setGravity(Gravity.CENTER_VERTICAL, 0, 50)

            }
        }
    }
}