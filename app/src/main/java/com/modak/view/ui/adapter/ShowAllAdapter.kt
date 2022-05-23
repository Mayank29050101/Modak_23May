package com.modak.view.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modak.R
import com.modak.view.AddCartActivity
import com.modak.view.ui.category.PopularModel
import kotlinx.android.synthetic.main.item_showall.view.*

class ShowAllAdapter(var context: Context,var popularList: ArrayList<PopularModel>) : RecyclerView.Adapter<ShowAllAdapter.Viewshow>() {
    class Viewshow(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewshow {
        val view=LayoutInflater.from(context).inflate(R.layout.item_showall,parent,false)
        return Viewshow(view)
    }

    override fun onBindViewHolder(holder: Viewshow, position: Int) {
        holder.itemView.txt_show_name_food.text=popularList.get(position).getpopular_name().toString()
        holder.itemView.txt_details_show.text=popularList.get(position).getpopular_detail().toString()
        holder.itemView.txt_show_rate.text=popularList.get(position).getrate_p().toString()
        Glide.with(context)
            .load(popularList.get(position).getimg_popular())
            .into(holder.itemView.img_show)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent= Intent(context, AddCartActivity::class.java)
            intent.putExtra("name_p",popularList.get(position).getpopular_name().toString())
            intent.putExtra("img_p",popularList.get(position).getimg_popular().toInt())
            intent.putExtra("rate",popularList?.get(position).getrate_p().toString())
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

}
