package com.modak.view.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.modak.R
import kotlinx.android.synthetic.main.fooditem.*


class BriyaniFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_briyani, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            //food_n!!.setText(bundle.getString("food_name"))
            food_n.text=bundle.getString("food_name")
            img_b.setImageResource(bundle.getInt("img"))
        }

//        val value = requireArguments().getString("food_name")
//        food_n.setText(value)

    }



}