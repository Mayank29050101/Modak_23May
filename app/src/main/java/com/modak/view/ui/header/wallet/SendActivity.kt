package com.modak.view.ui.header.wallet

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import kotlinx.android.synthetic.main.activity_send.*


class SendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Vouchers"
        btn_self.setOnClickListener(View.OnClickListener {
            btn_self.setTypeface(Typeface.DEFAULT_BOLD)
        })
        btn_friend.setOnClickListener(View.OnClickListener {
            btn_self.setTypeface(Typeface.DEFAULT_BOLD)
        })
        btn_proceed.setOnClickListener(View.OnClickListener {
            val animation=AnimationUtils.loadAnimation(applicationContext,R.anim.blink_button)
            btn_proceed.startAnimation(animation)
        })
        btn_radiogroup.setOnClickListener(View.OnClickListener {
            fun onRadioButtonClicked(view: View) {
                val checked = (view as RadioButton).isChecked
                var msg = ""
                when (view.getId()) {
                    R.id.btn_self ->{
                        if (checked) msg = "You Clicked For Self"
                        true
                    }

                    R.id.btn_friend ->{
                        if (checked) msg = "You Clicked For Friends/Family "
                        true
                    }
                    else -> {
                        finish()
                    }

                }
                true
                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
            }
        })
        txt_view_more.setOnClickListener(View.OnClickListener {
            showFirstBottomDialog()
        })
       // val transition: Transition = Slide(Gravity.BOTTOM)

    }

    private fun showFirstBottomDialog() {
        val bottomDialog = Dialog(this, R.style.BottomDialog)
        val contentView: View = LayoutInflater.from(this).inflate(R.layout.gift_dialog, null)
        bottomDialog.setContentView(contentView)
        bottomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = contentView.layoutParams
        layoutParams.width = resources.displayMetrics.widthPixels
        contentView.layoutParams = layoutParams
        bottomDialog.window!!.setGravity(Gravity.BOTTOM)
        bottomDialog.setCanceledOnTouchOutside(true)
        bottomDialog.window!!.setWindowAnimations(R.style.BottomDialog_Animation)
        bottomDialog.show()

        val button: ImageView =contentView.findViewById(R.id.btn_cancel)
        button.setOnClickListener {
            bottomDialog.dismiss()
        }
    }
}