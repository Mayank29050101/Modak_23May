package com.modak.view.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.modak.R
import com.modak.model.ResponsePojo.StateDataResponse


open class StateCodeSpinAdapter(
    context: Context, resource: Int, textviewId: Int,
    StateList: MutableList<StateDataResponse>
) : ArrayAdapter<StateDataResponse>(context, resource, textviewId, StateList) {


    internal var flater: LayoutInflater
    private var StateList: List<StateDataResponse>? = null
    private val resource: Int

    init {
        flater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.StateList = StateList
        this.resource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        val txt = view.findViewById(R.id.txtItemSpinner) as TextView
        txt.setText(StateList!!.get(position).name)
        return view

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        val txt = view!!.findViewById(R.id.txtItemSpinner) as TextView
        val llBackground = view.findViewById(R.id.llBackgroundDropDown) as LinearLayout
        llBackground.setBackgroundColor(context.resources.getColor(R.color.white))
        txt.setTextColor(ContextCompat.getColor(context, R.color.black))
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(20, 10, 20, 10)
        txt.layoutParams = params
        txt.setText(StateList!!.get(position).name)
        return view
    }

    override fun getCount(): Int {
        return StateList!!.size
    }

    fun setCountryCodeList(list: List<StateDataResponse>) {
        this.StateList = ArrayList<StateDataResponse>()
        this.StateList = list
        notifyDataSetChanged()
    }
}

