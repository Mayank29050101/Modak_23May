package com.modak.view.ui.conform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.modak.R
import kotlinx.android.synthetic.main.fragment_confirmation.*


class ConfirmationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        txt_date_to_display?.setText(intent.getStringExtra("date"))
//        txt_time_to_display!!.setText(intent.getStringExtra("time"))
        val bundle_c=this.arguments
        if(bundle_c !=null){
            txt_date_to_display.text=bundle_c.getString("date")
            txt_time_to_display.setText(bundle_c.getString("time"))
        }
    }


}