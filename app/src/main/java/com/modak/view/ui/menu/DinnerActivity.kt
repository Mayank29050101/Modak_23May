package com.modak.view.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class DinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinner)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Dinner"
    }
}