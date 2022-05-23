package com.modak.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class Address : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Address"

    }
}