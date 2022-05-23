package com.modak.view.ui.drawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.modak.R
import com.modak.view.ui.adapter.NotificationAdapter
import com.modak.view.ui.category.NotifyModal
import kotlinx.android.synthetic.main.fragment_notification.*

class NotificationFragment : Fragment() {
    private val notificationList=ArrayList<NotifyModal>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_notify.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val notify_adapter= NotificationAdapter(requireContext(),notificationList)
        recyclerview_notify.adapter=notify_adapter
        notification()



//            txt_city.setText("Notification")
////        (activity? as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        (activity as AppCompatActivity).supportActionBar!!.title = "Notification"
//        (activity as AppCompatActivity).supportActionBar!!.setTitle("Notification")
    }

    private fun notification() {
       var notify=NotifyModal(R.drawable.dp1,"Liked your dish","14/03/2022")
        notificationList.add(notify)
        notify= NotifyModal(R.drawable.dp2,"wants to order","14/03/2022")
        notificationList.add(notify)
        notify= NotifyModal(R.drawable.dp3,"review your app","14/03/2022")
        notificationList.add(notify)

    }
}