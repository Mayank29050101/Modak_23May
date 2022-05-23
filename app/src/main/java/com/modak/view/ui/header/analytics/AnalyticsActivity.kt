package com.modak.view.ui.header.analytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class AnalyticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Analytics"
    }
}