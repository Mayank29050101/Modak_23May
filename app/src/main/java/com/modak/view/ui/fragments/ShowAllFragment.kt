package com.modak.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.modak.R
import com.modak.view.ui.adapter.ShowAllAdapter
import com.modak.view.ui.category.PopularModel
import kotlinx.android.synthetic.main.fragment_show_all.*


class ShowAllFragment : Fragment() {
    private val popularList=ArrayList<PopularModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_all, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view_showall.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val showall_adapter= ShowAllAdapter(requireContext(),popularList)
        recycler_view_showall.adapter=showall_adapter
        popular()
    }

    private fun popular() {
        var popular=PopularModel("Chicken Briyani ","Fresh and spicy","₹350",R.drawable.banner_scroll,4)
        popularList.add(popular)
        popular=PopularModel(" Dosa ","Fresh ","₹300",R.drawable.bg_food_detail,4)
        popularList.add(popular)
        popular=PopularModel(" Chicken ","Spicy ","₹350",R.drawable.banner_scroll,3)
        popularList.add(popular)
        popular=PopularModel(" Pure Veg ","Fresh and unlimted dish ","₹200",R.drawable.indian,5)
        popularList.add(popular)
        popular=PopularModel(" North Indian ","spicy and freshly served and tastiest ","₹150",R.drawable.noth,5)
        popularList.add(popular)
        popular=PopularModel(" Chinese ","all type of chinese food available ","₹200",R.drawable.chinese,5)
        popularList.add(popular)
        popular=PopularModel(" Rolls & Sandwiches ","start your day by our freshly served breakfast ","₹200",R.drawable.rools,5)
        popularList.add(popular)
    }


}