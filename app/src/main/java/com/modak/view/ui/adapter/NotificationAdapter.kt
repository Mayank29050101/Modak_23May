package com.modak.view.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modak.R
import com.modak.view.ui.category.NotifyModal
import kotlinx.android.synthetic.main.notification_item.view.*

class NotificationAdapter(var context: Context,var notificationList: ArrayList<NotifyModal>) : RecyclerView.Adapter<NotificationAdapter.Viewnotify>() {
    class Viewnotify(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewnotify {
        val view=LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false)
        return Viewnotify(view)
    }

    override fun onBindViewHolder(holder: Viewnotify, position: Int) {
        holder.itemView.img_notify.setImageResource(notificationList.get(position).getimg())
        holder.itemView.txt_date.text=notificationList.get(position).getdate()
        holder.itemView.text_n.text=notificationList.get(position).gettxt_n()
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

}
