package com.modak.view.ui.payment_method

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import com.modak.view.ui.conform.Conform_M_Activity
import kotlinx.android.synthetic.main.activity_createpayementmethod.*

class CreatePaymentMethodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createpayementmethod)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Payment Method"
        save_button.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,Conform_M_Activity::class.java)
            startActivity(intent)
        })

    }


}