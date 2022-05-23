package com.modak.view.ui.header.wallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Add Money"
    }
}