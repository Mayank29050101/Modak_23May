package com.modak.view.ui.search

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.modak.R
import kotlinx.android.synthetic.main.fragment_south_indian.*


class SouthIndianFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_south_indian, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle_f=this.arguments
        if (bundle_f !=null){
            food_f.text=bundle_f.getString("bri")
            img_f.setImageResource(bundle_f.getInt("img_f"))
        }
        val spannable= SpannableString(food_f.text.toString())
        spannable.setSpan(
            UnderlineSpan(),
            0,
            food_f.text.toString().length,
            SpannableString.SPAN_EXCLUSIVE_INCLUSIVE
        )
        food_f.text=spannable
    }


}