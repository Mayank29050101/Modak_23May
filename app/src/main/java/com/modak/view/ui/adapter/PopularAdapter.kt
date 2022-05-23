package com.modak.view.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modak.R
import com.modak.model.ResponsePojo.ProductDataResponse
import com.modak.view.AddCartActivity
import kotlinx.android.synthetic.main.popular_item.view.*

class PopularAdapter(var context: Context, var popularList: List<ProductDataResponse>) : RecyclerView.Adapter<PopularAdapter.Viewpopular>() {
    class Viewpopular(itemView : View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewpopular {
        val popular=LayoutInflater.from(context).inflate(R.layout.popular_item,parent,false)
        return Viewpopular(popular)
    }

    override fun onBindViewHolder(holder: Viewpopular, position: Int) {
        holder.itemView.txt_popular_name.text=popularList.get(position).name
        holder.itemView.txt_popular_detail.text=popularList.get(position).spicy
        holder.itemView.txt_rate_p.text=popularList.get(position).price
//        holder.itemView.txt_rating_p.id=popularList.get(position).

        Glide.with(context)
            .load(popularList.get(position).photo)
            .into(holder.itemView.popular_img)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent= Intent(context, AddCartActivity::class.java)
            intent.putExtra("idkey",popularList.get(position).id!!.toInt())
            context.startActivity(intent)
        })
//        if (popularList.get(position).getpopular_name().equals("Chicken Briyani")|| popularList.get(position).getimg_popular().equals(R.drawable.banner_scroll)){
//            holder.itemView.setOnClickListener(View.OnClickListener {
//                val intent=Intent(context,AddCartActivity::class.java)
//                intent.putExtra("name_p",popularList?.get(position).getpopular_name().toString())
//                intent.putExtra("img_p",popularList?.get(position).getimg_popular().toInt())
//                intent.putExtra("rate",popularList?.get(position).getrate_p().toString())
//                context.startActivity(intent)
//            })
//        }
//        if (popularList.get(position).getpopular_name().equals("Dosa ")|| popularList.get(position).getimg_popular().equals(R.drawable.bg_food_detail)){
//            holder.itemView.setOnClickListener(View.OnClickListener {
//                val intent=Intent(context,AddCartActivity::class.java)
//                intent.putExtra("name_p",popularList.get(position).getpopular_name().toString())
//                intent.putExtra("img_p",popularList.get(position).getimg_popular().toInt())
//                context.startActivity(intent)
//            })
//        }
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

}
