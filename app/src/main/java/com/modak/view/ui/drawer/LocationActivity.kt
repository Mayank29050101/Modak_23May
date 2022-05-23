package com.modak.view.ui.drawer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import com.modak.view.BasketActivity
import kotlinx.android.synthetic.main.activity_location.*


class LocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " My Address"

        checkbox_add1.setOnClickListener(View.OnClickListener {
            checkbox_add1.isChecked=true
            checkbox_add2.isChecked=false
            checkbox_add3.isChecked=false
            checkbox_add4.isChecked=false
        })
        checkbox_add2.setOnClickListener(View.OnClickListener {
            checkbox_add1.isChecked=false
            checkbox_add2.isChecked=true
            checkbox_add3.isChecked=false
            checkbox_add4.isChecked=false
        })
        checkbox_add3.setOnClickListener(View.OnClickListener {
            checkbox_add1.isChecked=false
            checkbox_add2.isChecked=false
            checkbox_add3.isChecked=true
            checkbox_add4.isChecked=false
        })
        checkbox_add4.setOnClickListener(View.OnClickListener {
            checkbox_add1.isChecked=false
            checkbox_add2.isChecked=false
            checkbox_add3.isChecked=false
            checkbox_add4.isChecked=true
        })


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.option_search -> {
            true
        }
        R.id.option_filter -> {
//            val manager: FragmentManager = supportFragmentManager
//            val transaction: FragmentTransaction = manager.beginTransaction()
//            transaction.add(R.id.container,FilterFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
            true
        }
        R.id.option_cart -> {
            val intent=Intent(this,BasketActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            false
        }
    }


}