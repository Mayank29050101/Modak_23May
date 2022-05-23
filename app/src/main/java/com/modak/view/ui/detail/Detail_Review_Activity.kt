package com.modak.view.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.modak.R
import com.modak.helper.PrefUtils
import com.modak.helper.function
import com.modak.model.ResponsePojo.ProductDataResponse
import com.modak.model.ResponsePojo.ProductResponse
import com.modak.repositories.APIClient
import com.modak.view.ui.adapter.TabAdapter_d
import com.modak.view.ui.chef.ChefActivity
import com.modak.view.ui.custom.MDToast
import kotlinx.android.synthetic.main.activity_detail_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail_Review_Activity : AppCompatActivity() {
    var qty=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Detail"
        setContentView(R.layout.activity_detail_review)
        tablayout_details!!.addTab(tablayout_details!!.newTab().setText("Details"))
        tablayout_details!!.addTab(tablayout_details!!.newTab().setText("Reviews"))
        tablayout_details!!.tabGravity = TabLayout.GRAVITY_FILL
        fav_add.setOnClickListener(View.OnClickListener {
            if (fav_add.isSelected){
                fav_add.setImageResource(R.drawable.icon_add)
                fav_add.isSelected=false
//                function.showToast(this,"removed from favourite", MDToast.TYPE_WARNING)
            }
            else{
                fav_add.isSelected=true
                fav_add.setImageResource(R.drawable.checked_new)
                val animation= AnimationUtils.loadAnimation(this,R.anim.bounce)
                fav_add.startAnimation(animation)
                function.showToast(this,"Added to cart", MDToast.TYPE_SUCCESS)
            }
        })
        btn_plus_arrow.setOnClickListener(View.OnClickListener {
            if (qty>=1){
                qty += 1
                txt_review6.text=qty.toString()
            }
            else{
                txt_review6.text=qty.toString()
            }

        })
        btn_minus_arrow.setOnClickListener(View.OnClickListener {
            if ( qty>=2){
                qty -= 1
                txt_review6.text=qty.toString()
            }

            else {
                Toast.makeText(this, "cant be less than 1", Toast.LENGTH_SHORT).show()
            }
        })


        tablayout_details!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("Range")
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager_d!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        cardview1.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ChefActivity::class.java)
            startActivity(intent)
        })
        var favoriteList: List<ProductDataResponse> = arrayListOf()
        APIClient.getService().getProductid(
            "Bearer " + PrefUtils.getUserToken(this),
            intent.getIntExtra("id",0)
        ).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.body() != null) {
                    favoriteList = response.body()!!.response
                    // img_1.setImageResource(favoriteList.get(0).photo!!.toString())
                    Glide.with(this@Detail_Review_Activity)
                        .load(favoriteList.get(0).photo)
                        .into(img_1!!)
                    Glide.with(this@Detail_Review_Activity)
                        .load(favoriteList.get(0).photo)
                        .into(ImageProduct!!)
                    //  img_1.setImageResource(favoriteList.get(0).photo!!.toInt())
                    rava_d.text = favoriteList.get(0).spicy.toString()
                    txt_d4.text = favoriteList.get(0).price.toString()
                    txt_d1.text = favoriteList.get(0).name.toString()
                    PrefUtils.setrecipe(this@Detail_Review_Activity, favoriteList.get(0).recipe.toString())

                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
            }
        })


        val adapter = TabAdapter_d(
            this,
           this?.supportFragmentManager!!,
            2,
        )
        viewpager_d!!.adapter = adapter
        viewpager_d!!.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tablayout_details
            )
        )
    }
}