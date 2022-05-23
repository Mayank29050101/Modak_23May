package com.modak.view.ui.header

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class ModakWalletActivity : AppCompatActivity() {
    companion object{
        fun launchActivity(
            activity : ModakMoneyActivity
        )
        {
            if (activity!=null) {
            var intent=Intent(activity,ModakWalletActivity::class.java)
            activity.startActivity(intent)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modak_wallet)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " Modak Wallet"
    }
}