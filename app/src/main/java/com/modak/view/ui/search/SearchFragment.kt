package com.modak.view.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.modak.R
import com.modak.view.ui.adapter.SearchAdapter
import com.modak.view.ui.category.SearchModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search.*


class SearchFragment : Fragment() {
    val searchList = ArrayList<SearchModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Search()
        search_refresh.setOnRefreshListener {
            Search()
            search_refresh.isRefreshing=false
        }

    }

    private fun Search() {
        recycler_view_search.layoutManager = GridLayoutManager(requireContext(), 2)
        val search_adapter = SearchAdapter(requireContext(), searchList)
        recycler_view_search.adapter = search_adapter
        search()
        txt_search_s.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                //filter(s.toString())
            }
        })
        return
    }

    private fun filter(text: String) {
       val newList: ArrayList<SearchModel> = ArrayList()
        for (item in newList) {
            if (item.txt_search!!.contains(text)) {
                newList.add(item)
            }
            if (newList.isEmpty()){
                Toast.makeText(requireContext(), "ntg found", Toast.LENGTH_SHORT).show()
            }
            else{

            }
        }
        val search_adapter=SearchAdapter(requireContext(),searchList)
        search_adapter.filterList(searchList)
    }

       private fun search() {
           var search = SearchModel(R.drawable.briyani, "Briyani")
           searchList.add(search)
           search = SearchModel(R.drawable.pizzas, "Pizzas")
           searchList.add(search)
           search = SearchModel(R.drawable.indian, "Pure veg")
           searchList.add(search)
           search = SearchModel(R.drawable.burger, "Burger")
           searchList.add(search)
           search = SearchModel(R.drawable.chinese, "Chinese")
           searchList.add(search)
           search = SearchModel(R.drawable.cake, "Cake & Dessert")
           searchList.add(search)
           search = SearchModel(R.drawable.noth, "North Indian")
           searchList.add(search)
           search = SearchModel(R.drawable.bg_food_detail, "South Indian")
           searchList.add(search)
           search = SearchModel(R.drawable.rools, "Rolls & Sandwiches")
           searchList.add(search)
           search = SearchModel(R.drawable.icecream, "Ice cream")
           searchList.add(search)
           search = SearchModel(R.drawable.tea, "Tea & Beverage")
           searchList.add(search)
           search = SearchModel(R.drawable.sweet, "Sweets")
           searchList.add(search)
       }
    }







