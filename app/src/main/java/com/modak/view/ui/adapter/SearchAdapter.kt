package com.modak.view.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.modak.R
import com.modak.view.ui.category.SearchModel
import com.modak.view.ui.search.BriyaniFragment
import com.modak.view.ui.search.PizzaFragment
import kotlinx.android.synthetic.main.search_item.view.*


class SearchAdapter(var context: Context, var searchList: ArrayList<SearchModel>) : RecyclerView.Adapter<SearchAdapter.Viewsearch>() {
    class Viewsearch(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val text_search : TextView
        private val search_img: ImageView

        init {
            text_search = itemView.findViewById(R.id.txt_search)
            search_img = itemView.findViewById(R.id.img_search)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewsearch {
        val view=LayoutInflater.from(context).inflate(R.layout.search_item,parent,false)
        return Viewsearch(view)
    }

    override fun onBindViewHolder(holder: Viewsearch, position: Int) {
        holder.itemView.txt_search.text=searchList.get(position).txt_search
        holder.itemView.img_search.setImageResource(searchList.get(position).img_search)


        if(searchList.get(position).txt_search.equals("Briyani") || searchList.get(position).img_search.equals(R.drawable.briyani)) {
           holder.itemView.setOnClickListener {
               val fragment: Fragment = BriyaniFragment()
               val args = Bundle()
               args.putString("food_name",searchList?.get(position).txt_search )
               args.putInt("img",searchList?.get(position).img_search)
               fragment.arguments=args
               val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager
               val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
               fragmentTransaction.replace(R.id.frameContainer, fragment)
               fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
               fragmentTransaction.addToBackStack(null)
               fragmentTransaction.commit()
           }
       }
        if(searchList.get(position).txt_search.equals("Pizzas") || searchList.get(position).img_search.equals(R.drawable.pizzas)) {
            holder.itemView.setOnClickListener {
                Toast.makeText(context, "Pizzas", Toast.LENGTH_SHORT).show()
                val fragment: Fragment = PizzaFragment()
                val args = Bundle()
                args.putString("name",searchList?.get(position).txt_search )
                fragment.arguments=args
                val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameContainer, fragment)
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }

//
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
    fun getFilter(): Filter? {
        return Searched_Filter
    }

    private val Searched_Filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: ArrayList<SearchModel> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(searchList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in searchList) {
                    if (item.txt_search!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            searchList.clear()
            searchList.addAll(results.values as ArrayList<SearchModel>)
            notifyDataSetChanged()
        }
    }
//    fun filterList(searchList: ArrayList<SearchModel>): Filter {
//        this.searchList=searchList!!
//        notifyDataSetChanged()
//        return filterList(searchList)
//    }
    fun filterLists(filteredNames: ArrayList<SearchModel>) {
        this.searchList = filteredNames
        notifyDataSetChanged()
    }
//     fun filterList(filterdNames: ArrayList<SearchModel?>) {
//    this.searchList = filterdNames
//    notifyDataSetChanged()
//}
fun filterList(filterllist: ArrayList<SearchModel>) {
    // below line is to add our filtered
    // list in our course array list.
    searchList = filterllist
    // below line is to notify our adapter
    // as change in recycler view data.
    notifyDataSetChanged()
}
    fun setData(search: ArrayList<SearchModel>) {
        searchList = search
        notifyDataSetChanged()
    }

}
