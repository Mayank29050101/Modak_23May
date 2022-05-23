package com.modak.view.ui.header.functionalities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class PassBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_book)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Passbook"
    }
}