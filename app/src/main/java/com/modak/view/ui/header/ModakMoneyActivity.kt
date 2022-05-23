package com.modak.view.ui.header

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.modak.R
import com.modak.view.ui.header.analytics.AnalyticsActivity
import com.modak.view.ui.header.functionalities.NearbyActivity
import com.modak.view.ui.header.functionalities.PassBookActivity
import com.modak.view.ui.header.functionalities.ReminderActivity
import com.modak.view.ui.header.functionalities.StatementActivity
import com.modak.view.ui.header.wallet.AddActivity
import com.modak.view.ui.header.wallet.PayActivity
import com.modak.view.ui.header.wallet.SendActivity
import com.modak.view.ui.header.wallet.TransferActivity
import kotlinx.android.synthetic.main.activity_modak_money.*

class ModakMoneyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallet)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " Modak Money"
        layout_wallet.visibility=View.GONE
        view_layout.setOnClickListener(View.OnClickListener {

            if (layout_wallet.isVisible){
                view_layout.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                layout_wallet.visibility=View.GONE
            }
            else{
                view_layout.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                layout_wallet.visibility=View.VISIBLE
            }
        })
        layout_wallet.setOnClickListener(View.OnClickListener {
//            val intent=Intent(this,ModakWalletActivity::class.java)
//            startActivity(intent)
            ModakWalletActivity.launchActivity(this)
        })
        linear_pay.setOnClickListener(View.OnClickListener {
//            val pay=Intent(this,PayActivity::class.java)
//            startActivity(pay)
            PayActivity.launchActivity(this)
        })
        linear_transfer.setOnClickListener(View.OnClickListener {
//            val transfer=Intent(this,TransferActivity::class.java)
//            startActivity(transfer)
            TransferActivity.launchActivity(this)
        })
        linear_send.setOnClickListener(View.OnClickListener {
            val send=Intent(this,SendActivity::class.java)
            startActivity(send)
        })
        linear_add.setOnClickListener(View.OnClickListener {
            val add=Intent(this,AddActivity::class.java)
            startActivity(add)
        })

        card_analytics.setOnClickListener(View.OnClickListener {
            val analytics=Intent(this,AnalyticsActivity::class.java)
            startActivity(analytics)
        })


        constraint_passbook.setOnClickListener(View.OnClickListener {
            val passbook=Intent(this,PassBookActivity::class.java)
            startActivity(passbook)
        })
        constraint_nearby.setOnClickListener(View.OnClickListener {
            val nearby=Intent(this,NearbyActivity::class.java)
            startActivity(nearby)
        })
        constraint_reminder.setOnClickListener(View.OnClickListener {
            val reminder=Intent(this,ReminderActivity::class.java)
            startActivity(reminder)
        })
        constraint_statement.setOnClickListener(View.OnClickListener {
            val statement=Intent(this,StatementActivity::class.java)
            startActivity(statement)
        })
    }
}