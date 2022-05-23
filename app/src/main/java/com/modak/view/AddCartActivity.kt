package com.modak.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.modak.R
import com.modak.helper.PrefUtils
import com.modak.helper.function
import com.modak.model.ResponsePojo.ProductDataResponse
import com.modak.model.ResponsePojo.ProductResponse
import com.modak.repositories.APIClient
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.payment_method.PaymentMethodActivity
import kotlinx.android.synthetic.main.activity_add_cart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCartActivity : AppCompatActivity() {
    var images = intArrayOf(R.drawable.heart_n,R.drawable.heart_f)
    var fire = intArrayOf(R.drawable.fire_fill)
    var i = 0
    var qty=1
    private var favoriteList:List<ProductDataResponse> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cart)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar!!.hide()

        APIClient.getService().getProductid(
            "Bearer " + PrefUtils.getUserToken(this),
            intent.getIntExtra("idkey",0)
        ).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.body() != null) {
                    favoriteList = response.body()!!.response
                    // img_1.setImageResource(favoriteList.get(0).photo!!.toString())
                    Glide.with(this@AddCartActivity)
                        .load(favoriteList.get(0).photo)
                        .into(img_p!!)
                    txt_selected_food.text=favoriteList.get(0).name.toString()
                    item_rate.text=favoriteList.get(0).price.toString()
                    //  img_1.setImageResource(favoriteList.get(0).photo!!.toInt())

                    PrefUtils.setrecipe(this@AddCartActivity, favoriteList.get(0).recipe.toString())

                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
            }
        })
        like.setOnClickListener(View.OnClickListener {
            if (like.isSelected){
                like.isSelected=false
                function.showToast(this,"removed from favourite",MDToast.TYPE_SUCCESS)
            }
            else{
                like.isSelected=true
                val animation=AnimationUtils.loadAnimation(applicationContext,R.anim.bounce)
                like.startAnimation(animation)
                function.showToast(this,"Added to favourite",MDToast.TYPE_SUCCESS)
            }
        })
        btn_addtocart.setOnClickListener(View.OnClickListener {
           // MDToast.makeText(this,"Added to cart",MDToast.LENGTH_SHORT,MDToast.TYPE_SUCCESS)
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()
        })
        add_btn.setOnClickListener(View.OnClickListener {
            if (qty>=1){
                qty += 1
                txt_qty_s.text=qty.toString()
            }
            else{
                txt_qty_s.text=qty.toString()
            }

        })
        del_btn.setOnClickListener(View.OnClickListener {
                if ( qty>=2){
                    qty -= 1
                    txt_qty_s.text=qty.toString()
                }

            else {
                Toast.makeText(this, "cant be less than 1", Toast.LENGTH_SHORT).show()
            }
        })
        s_size.setOnClickListener {
            s_size.setTextColor(resources.getColor(R.color.red))
            m_size.setTextColor(resources.getColor(R.color.black))
            l_size.setTextColor(resources.getColor(R.color.black))
            f_size.setTextColor(resources.getColor(R.color.black))
        }
        m_size.setOnClickListener {
            s_size.setTextColor(resources.getColor(R.color.black))
            m_size.setTextColor(resources.getColor(R.color.red))
            l_size.setTextColor(resources.getColor(R.color.black))
            f_size.setTextColor(resources.getColor(R.color.black))
        }
        l_size.setOnClickListener {
            s_size.setTextColor(resources.getColor(R.color.black))
            m_size.setTextColor(resources.getColor(R.color.black))
            l_size.setTextColor(resources.getColor(R.color.red))
            f_size.setTextColor(resources.getColor(R.color.black))
        }
        f_size.setOnClickListener {
            s_size.setTextColor(resources.getColor(R.color.black))
            m_size.setTextColor(resources.getColor(R.color.black))
            l_size.setTextColor(resources.getColor(R.color.black))
            f_size.setTextColor(resources.getColor(R.color.red))
        }

        fire_s1.setOnClickListener {
            fire_s1.setImageResource(R.drawable.fire_fill)
            fire_s1.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.fires1)))
            fire_s2.setImageResource(R.drawable.fire)
            fire_s3.setImageResource(R.drawable.fire)
            fire_s4.setImageResource(R.drawable.fire)
        }
        fire_s2.setOnClickListener {
            fire_s2.setImageResource(R.drawable.fire_fill)
            fire_s1.setImageResource(R.drawable.fire)
            fire_s3.setImageResource(R.drawable.fire)
            fire_s4.setImageResource(R.drawable.fire)
            fire_s2.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.fires2)))
        }
        fire_s3.setOnClickListener {
            fire_s3.setImageResource(R.drawable.fire_fill)
            fire_s1.setImageResource(R.drawable.fire)
            fire_s2.setImageResource(R.drawable.fire)
            fire_s4.setImageResource(R.drawable.fire)
            fire_s3.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.fires3)))
        }
        fire_s4.setOnClickListener {
            fire_s4.setImageResource(R.drawable.fire_fill)
            fire_s1.setImageResource(R.drawable.fire)
            fire_s2.setImageResource(R.drawable.fire)
            fire_s3.setImageResource(R.drawable.fire)
            fire_s4.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))
        }

        btn_addtocart.setOnClickListener(View.OnClickListener {
            val intent=Intent(this, PaymentMethodActivity::class.java)
            startActivity(intent)
        })

    }
}