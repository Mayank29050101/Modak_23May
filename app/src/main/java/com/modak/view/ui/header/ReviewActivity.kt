package com.modak.view.ui.header

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " Review"
    }
}