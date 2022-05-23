package com.modak.view.ui.adapter

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.modak.R
import com.modak.view.ui.category.FoodModel
import com.modak.view.ui.search.SouthIndianFragment
import kotlinx.android.synthetic.main.card_shop_food.view.*


class FoodAdapter(var context: Context,var foodList: ArrayList<FoodModel>): RecyclerView.Adapter<FoodAdapter.viewfood>() {
    class viewfood(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val text_foood : TextView
        private val food_img: ImageView

        init {
            // initializing our views with their ids.
            text_foood = itemView.findViewById(R.id.txt_food_name)
            food_img = itemView.findViewById(R.id.img_food)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewfood {
        val view=LayoutInflater.from(context).inflate(R.layout.card_shop_food,parent,false)
        return viewfood(view)
    }
//app:cardUseCompatPadding="true"
    override fun onBindViewHolder(holder: viewfood, position: Int) {
        holder.itemView.txt_food_name.text=foodList.get(position).food_name
//        Glide.with(context)
//            .load(foodList.get(position).getimg_food())
//            .into(holder.itemView.img_food)
        holder.itemView.img_food.setImageResource(foodList.get(position).img_food)



   // if (foodList.get(position).food_name.equals("Dosa")|| foodList.get(position).img_food.equals(R.drawable.bg_food_detail))
        holder.itemView.setOnClickListener(View.OnClickListener {
            val fragment:Fragment= SouthIndianFragment()
            val bundle_f= Bundle()
            bundle_f.putString("bri",foodList.get(position).food_name.toString())
            bundle_f.putInt("img_f",foodList.get(position).img_food)
            fragment.arguments=bundle_f
            val fragmentManager:FragmentManager=(context as FragmentActivity).supportFragmentManager
            val fragmentTransaction : FragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameContainer,fragment)
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            val spannable=SpannableString(foodList.get(position).food_name.toString())
            spannable.setSpan(
                UnderlineSpan(),
                0,
                foodList.get(position).food_name!!.toString().length,
                SpannableString.SPAN_EXCLUSIVE_INCLUSIVE
            )
            holder.itemView.txt_food_name.text=spannable
        })



    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    }


