package com.modak.view.ui.drawer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.modak.R
import com.modak.view.SelectCityActivity
import com.modak.view.ui.adapter.DeliveryAdapter
import com.modak.view.ui.category.DeliveryfModel
import com.modak.view.ui.conform.ConfirmationFragment
import kotlinx.android.synthetic.main.fragment_delivery.*

class MyProfileFragment : Fragment() {
    private val deliveryList=ArrayList<DeliveryfModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize data.
        /*val myDataset = FavoriteModel().loadfav()
        recyclerview.adapter = Favorite_Adapter(this, myDataset)*/

        return inflater.inflate(R.layout.activity_my_profile, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        img_city.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity, SelectCityActivity::class.java)
            activity?.startActivity(intent)
        })
        recyclerview_fav_d.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        val delivery_adapter= DeliveryAdapter(requireContext(),deliveryList)
        recyclerview_fav_d.adapter=delivery_adapter
        deliverf()
        btn_confirmation.setOnClickListener(View.OnClickListener {
            val newFragment:Fragment= ConfirmationFragment()
            val transaction: FragmentTransaction =requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameContainer,newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.blink_button)
            btn_confirmation.startAnimation(animation)
        })
    }

    private fun deliverf() {
        var deliveryf= DeliveryfModel(R.drawable.banner_scroll)
        deliveryList.add(deliveryf)
        deliveryf= DeliveryfModel(R.drawable.bg_food_detail)
        deliveryList.add(deliveryf)
        deliveryf= DeliveryfModel(R.drawable.banner_scroll)
        deliveryList.add(deliveryf)
        deliveryf= DeliveryfModel(R.drawable.bg_food_detail)
        deliveryList.add(deliveryf)
        deliveryf= DeliveryfModel(R.drawable.banner_scroll)
        deliveryList.add(deliveryf)
    }
}