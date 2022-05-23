package com.modak.helper

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.modak.R
import java.util.*


open class BaseActivity : AppCompatActivity() {
    var screenWidth: Int = 0
    var screenHeight: Int = 0
    var selectedScreen = 0
    var fragmentBackStack: Stack<Fragment>? = null
    var showBackMessage: Boolean? = true
    var doubleBackToExitPressedOnce: Boolean = false
    var dialog: ProgressDialog? = null
    var customdialog: Dialog? = null


    fun setShowBackMessage(showBackMessage: Boolean) {
        this.showBackMessage = showBackMessage
    }

    fun getFragments(): Stack<Fragment>? {
        return fragmentBackStack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            fragmentBackStack = Stack()
            ProductsFragment()
            BlankFragment()
            getWidthAndHeight()
        } catch (e: Exception) {
            val packageManager = this.getPackageManager()
            val intent = packageManager.getLaunchIntentForPackage(this.getPackageName())
            val componentName = intent!!.getComponent()
            val mainIntent = Intent.makeRestartActivityTask(componentName)
            startActivity(mainIntent)
            System.exit(0)
        }


    }

    fun ProductsFragment() {
        println("<<><ErrorComming1")

    }

    fun BlankFragment() {
        println("<<><ErrorComming2")
        // doesn't do anything special
    }


    fun getWidthAndHeight() {
        val displaymetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymetrics)
        screenHeight = displaymetrics.heightPixels
        screenWidth = displaymetrics.widthPixels
    }

    @Synchronized
    fun pushAddFragments(fragment: Fragment?, shouldAnimate: Boolean, shouldAdd: Boolean) {
        try {
            if (fragment != null) {
                fragmentBackStack?.push(fragment)
                val manager = supportFragmentManager
                val ft = manager.beginTransaction()
                ft.replace(R.id.frameContainer, fragment, fragmentBackStack?.size.toString())
                ft.commit()
                manager.executePendingTransactions()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun popFragments(shouldAnimate: Boolean) {
        var fragment: Fragment? = null
        if (fragmentBackStack!!.size > 1)
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 2)
        else if (!fragmentBackStack!!.isEmpty())
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1)
        var manager: FragmentManager = supportFragmentManager

        var ft: FragmentTransaction = manager.beginTransaction()

        if (fragment != null) {
            manager.executePendingTransactions()
            if (fragment.isAdded())
                if (fragmentBackStack!!.size > 1) {
                    ft.show(fragment).commit()
                } else
                    ft.replace(R.id.frameContainer, fragment).commit()
            fragmentBackStack!!.pop()
        }
        manager.executePendingTransactions()
    }

    fun doubleTapOnBackPress() {
        finish()
        //Functions.showToast(this, "Press again to exit" + "!!", MDToast.TYPE_ERROR)
    }





    fun showProgressDialog(isCancelable: Boolean) {
        try {

            println("<><><error111111222")
            if (dialog != null && dialog?.isShowing!!) {
                dialog!!.dismiss()
            }
            dialog = ProgressDialog(this)
            dialog!!.setMessage(getString(R.string.msg_please_wait))
            dialog!!.setCancelable(isCancelable)
            dialog!!.show()
            println("<><><><>per ok showd")

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun hideProgressDialog() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null) {
            dialog!!.dismiss()
        }
        if (customdialog != null) {
            customdialog!!.dismiss()
        }
    }

    fun showProgress(ctx: Context) {

        if (customdialog != null) {
            try {
                if (customdialog!!.isShowing()) {
                    customdialog!!.dismiss()
                }
            } catch (e: Exception) {
            }
        }
        try {
            customdialog = Dialog(ctx, R.style.ActivityDialog);
            customdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customdialog!!.getWindow()!!.setGravity(Gravity.CENTER)
            customdialog!!.setCancelable(false)
           // customdialog!!.setContentView(R.layout.custom_progressbar)
            customdialog!!.show()
            println("<><><><>per ok show")
        } catch (e: Exception) {
        }
    }

    fun closeProgress() {
        try {
            if (customdialog != null && customdialog!!.isShowing()) {
                customdialog!!.dismiss()
            }
        } catch (e: Exception) {
        }

    }




}