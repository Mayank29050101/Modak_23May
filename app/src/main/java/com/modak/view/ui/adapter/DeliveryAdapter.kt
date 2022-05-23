package com.modak.view.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modak.R
import com.modak.view.ui.category.DeliveryfModel
import kotlinx.android.synthetic.main.fav_d_item.view.*

class DeliveryAdapter(var context: Context,var deliveryList: ArrayList<DeliveryfModel>) : RecyclerView.Adapter<DeliveryAdapter.Viewdeliveryf>() {
    class Viewdeliveryf(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewdeliveryf {
        val view=LayoutInflater.from(context).inflate(R.layout.fav_d_item,parent,false)
        return Viewdeliveryf(view)
    }

    override fun onBindViewHolder(holder: Viewdeliveryf, position: Int) {
        holder.itemView.img_fav_d.setImageResource(deliveryList.get(position).img_fav_d)
    }

    override fun getItemCount(): Int {
         return deliveryList.size
    }

}
