package com.modak.view


import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import kotlinx.android.synthetic.main.activity_changepassword.*

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Change Password "
        btn_confirm.setOnClickListener(View.OnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext,R.anim.blink_button)
            btn_confirm.startAnimation(animation)
        })
//        btn_confirm.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this, "Password Change successfully", Toast.LENGTH_LONG).show()
//
//
//        })
    }
}