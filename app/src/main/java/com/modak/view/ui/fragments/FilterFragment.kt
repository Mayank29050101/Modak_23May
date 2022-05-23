package com.modak.view.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.modak.R
import com.modak.view.ui.conform.ConfirmationFragment
import com.modak.view.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_filter.*


class FilterFragment : Fragment(),DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {


        var day = 0
        var month: Int = 0
        var year: Int = 0
        var hour: Int = 0
        var minute: Int = 0
        var myDay = 0
        var myMonth: Int = 0
        var myYear: Int = 0
        var myHour: Int = 0
        var myMinute: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        img_cancel.setOnClickListener(View.OnClickListener {
            val intent=Intent(activity,DashboardActivity::class.java)
            activity?.startActivity(intent)
        })
        l_time.visibility=View.GONE
        linear_dis.visibility=View.GONE
        btn_time.setOnClickListener(View.OnClickListener {
            if (l_time.isVisible){
                btn_time.setImageResource(R.drawable.plus_n)
               // btn_time.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))
                l_time.visibility=View.GONE
            }
            else{
                btn_time.setImageResource(R.drawable.minus_)
               // btn_time.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))
                l_time.visibility=View.VISIBLE
            }
        })

        btn_ad_on.setOnClickListener(View.OnClickListener {
            if (linear_dis.isVisible){
                btn_ad_on.setImageResource(R.drawable.plus_n)
               // btn_ad_on.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))
                linear_dis.visibility=View.GONE
            }
            else{
                btn_ad_on.setImageResource(R.drawable.minus_)
               // btn_ad_on.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))
                linear_dis.visibility=View.VISIBLE
            }
        })
        apply.setOnClickListener(View.OnClickListener {

            val fragment:Fragment=ConfirmationFragment()
            val build_c=Bundle()
            build_c.putString("date",txt_view_date.text.toString())
            build_c.putString("time",txt_time.text.toString())
            fragment.arguments=build_c
            val fragmentManager:FragmentManager=(context as FragmentActivity).supportFragmentManager
            val fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameContainer,fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })

        btn_calendar.setOnClickListener {
            day = 0
             month = 0
             year = 0
            val calendar: Calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireContext(), this , year, month, day)
            datePickerDialog.show()

        }
        }
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            myDay = dayOfMonth
            myYear = year
            myMonth = month + 1
            val calendar: Calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            hour = calendar.get(Calendar.HOUR)
            minute = calendar.get(Calendar.MINUTE)

            txt_view_date.text = "Date : "+ myDay+"/"+ myMonth+"/" + myYear
           val timePickerDialog = TimePickerDialog(requireContext(), this, hour, minute,
                DateFormat.is24HourFormat(requireContext()))
            timePickerDialog.show()
        }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

            myHour = hour
            myMinute = minute

        txt_time.text =  ":"  + myHour + ":" + myMinute


    }

}

