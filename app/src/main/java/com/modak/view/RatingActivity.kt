package com.modak.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modak.view.ui.custom.MDToast
import com.modak.R
import com.modak.helper.function
import kotlinx.android.synthetic.main.activity_rating.*

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        if (rBar != null) {
            btn_sr?.setOnClickListener {
                val msg = rBar.rating.toString()
                function.showToast(this,"Rating is : $msg", MDToast.TYPE_SUCCESS)
            }
        }
    }
}