package com.modak.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import kotlinx.android.synthetic.main.activity_basket.*

class BasketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Basket"

    }

    override fun onBackPressed() {
        super.onBackPressed()
        basket_f.text.clear()
        edt_add1.text.clear()
        edt_add2.text.clear()
        edt_country.text.clear()
        edt_phoneno.text.clear()
        edt_homeadd.text.clear()
        edt_lastname.text.clear()
        edt_zip.text.clear()
    }
}