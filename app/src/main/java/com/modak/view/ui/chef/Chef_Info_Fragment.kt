package com.modak.view.ui.chef

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.modak.R
import kotlinx.android.synthetic.main.fragment_chef__info_.*


class Chef_Info_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chef__info_, container, false)
    }

    @SuppressLint("Range")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txt_chef_info.setMovementMethod(ScrollingMovementMethod())
        txt_chef_info.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        txt_chef_info.setText("A chef is a trained professional cook and tradesman who is proficient in all aspects of food preparation, often focusing on a particular cuisine. The word \"chef\" is derived from the term chef de cuisine (French pronunciation: \u200B[ʃɛf.də.kɥi.zin]), the director or head of a kitchen. Chefs can receive formal training from an institution, as well as by apprenticing with an experienced chef.\n" +
                "\n" +
                "In the UK, the title executive chef normally applies to hotels with multi outlets in the same hotel. Other establishments in the UK tend to use the title head chef.")

        btShowmore.setOnClickListener(View.OnClickListener {
           if (btShowmore.getText().toString().equals("Showmore",ignoreCase = true))
           {
                val animation = ObjectAnimator.ofInt(txt_chef_info, "maxLines", 40)
                animation.setDuration(100).start()
                btShowmore.setText("Showless")
            }
            else
            {
                val animation = ObjectAnimator.ofInt(txt_chef_info, "maxLines", 4)
                animation.setDuration(100).start()
                btShowmore.setText("Showmore")
            }

        })

    }


}