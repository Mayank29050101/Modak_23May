package com.modak

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class ModakApplication : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: ModakApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //session()
    }

//    private fun session() {
//            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        var logintime:String=PrefUtils.getLogintime(this).toString().trim()
//        var expire:String=PrefUtils.getExpiries(this).toString().trim()
//            val date1 = sdf.parse(logintime)
//            val date2 = sdf.parse(expire)
//
//            // after() method
//            if (date1.after(date2)) {
//                println("$date1 is after $date2")
//            }
//
//            // before() method
//            if (date1.before(date2)) {
//                println("$date1 is before $date2")
//            }
//
//            // equals() method
//            if (date1 == date2) {
//                println("$date1 is equal to $date2")
//            }
//
//            // compareTo() method
//            val diff = date1.compareTo(date2)
//            if (diff > 0) {
//                println("$date1 is greater than $date2")
//                val intent= Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            } else if (diff < 0) {
//                println("$date1 is less than $date2")
//            } else {
//                println("$date1 is equal to $date2")
//            }
//
//    }
}