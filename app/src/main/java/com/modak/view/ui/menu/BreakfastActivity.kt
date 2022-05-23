package com.modak.view.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class BreakfastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Breakfast"
    }
}