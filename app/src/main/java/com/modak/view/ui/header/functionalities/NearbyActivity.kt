package com.modak.view.ui.header.functionalities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class NearbyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Cash Deposit"
    }
}