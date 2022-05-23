package com.modak.view.ui.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modak.R
import com.modak.helper.function
import com.modak.model.ResponsePojo.ProductDataResponse
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.detail.Detail_Review_Activity
import kotlinx.android.synthetic.main.favorite_item.view.*


class FavoruteAdapter(var context: Context, var favoriteList: List<ProductDataResponse>) : RecyclerView.Adapter<FavoruteAdapter.Viewfav>() {
    class Viewfav(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewfav {
        val fav=LayoutInflater.from(context).inflate(R.layout.favorite_item,parent,false)
        return Viewfav(fav)
    }

    override fun onBindViewHolder(holder: Viewfav, position: Int) {
        holder.itemView.txt_favchef.text=favoriteList.get(position).name
        holder.itemView.txt_favrate.text=favoriteList.get(position).price
        holder.itemView.rava.text=favoriteList.get(position).description

        Glide.with(context)
            .load(favoriteList.get(position).photo)
            .into(holder.itemView.img_fav)
        Glide.with(context)
            .load(favoriteList.get(position).photo)
            .into(holder.itemView.img_fav_profile)
        holder.itemView.fav_img_smile.setOnClickListener(View.OnClickListener {
            if (holder.itemView.fav_img_smile.isSelected){
                holder.itemView.fav_img_smile.setImageResource(R.drawable.smile)
                holder.itemView.fav_img_smile.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))
                holder.itemView.fav_img_smile.isSelected=false
                function.showToast(context,"removed from favourite",MDToast.TYPE_WARNING)
            }
            else{
                holder.itemView.fav_img_smile.isSelected=true
                holder.itemView.fav_img_smile.setImageResource(R.drawable.happy)
                holder.itemView.fav_img_smile.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))
                val animation= AnimationUtils.loadAnimation(context,R.anim.bounce)
                holder.itemView.fav_img_smile.startAnimation(animation)
                function.showToast(context,"Added to favourite",MDToast.TYPE_SUCCESS)
            }
        })
        holder.itemView.setOnClickListener(View.OnClickListener {
//            val fragment: Fragment = Detail_Review_Fragment()
//            val bundle= Bundle()
//            PrefUtils.setrecipe(context,favoriteList.get(position).recipe)
//            bundle.putInt("key",favoriteList.get(position).id!!.toInt())
//            fragment.arguments=bundle
//            val fragmentManager:FragmentManager = (context as FragmentActivity).supportFragmentManager
//            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.frameContainer, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
            val intent=Intent(context,Detail_Review_Activity::class.java)
            intent.putExtra("id",favoriteList.get(position).id!!.toInt())
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

}
