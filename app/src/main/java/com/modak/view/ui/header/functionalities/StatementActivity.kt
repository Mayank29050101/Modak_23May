package com.modak.view.ui.header.functionalities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R

class StatementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Request Statement"
    }
}