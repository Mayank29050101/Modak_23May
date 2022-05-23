package com.modak.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import kotlinx.android.synthetic.main.activity_review_order.*


class ReviewOrderActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Review Order"

        val intent = intent
        val str = intent.getStringExtra("message_key")
        txt_review2.setText(str)

//        txt_review2.text=getText(txt_Time.text).toString()


    }

    fun clickButton(view: View) {
        if (view.id == R.id.btn_confirm_order) {
            btn_confirm_order.setBackgroundResource(R.drawable.btn_confirm)
            val intent=Intent(this,BasketActivity::class.java)
            startActivity(intent)
        }
        if (view.id == R.id.btn_continue_browsing) {
            btn_continue_browsing.setBackgroundResource(R.drawable.btn_confirm)
        }

    }
}