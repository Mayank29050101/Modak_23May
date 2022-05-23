package com.modak.view.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class LunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Lunch"
    }
}