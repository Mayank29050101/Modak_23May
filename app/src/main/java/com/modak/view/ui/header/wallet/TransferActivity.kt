package com.modak.view.ui.header.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import com.modak.view.ui.header.ModakMoneyActivity

class TransferActivity : AppCompatActivity() {
    companion object{
        fun launchActivity(
            activity: ModakMoneyActivity
        ){
            if (activity!=null){
                var intent=Intent(activity,TransferActivity::class.java)
                activity.startActivity(intent)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Transfer Money"
    }
}