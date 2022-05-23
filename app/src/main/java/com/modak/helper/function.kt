package com.modak.helper

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Gravity
import android.view.Window
import com.modak.R
import com.modak.view.ui.custom.MDToast
import kotlinx.android.synthetic.main.custom_progressbar.*


object function {
    var customdialog: Dialog? = null
        fun showToast(context: Context, message: String?, type: Int) {
            val mdToast = MDToast.makeText(context, message?.toString(), MDToast.LENGTH_SHORT, type)
            mdToast.show()
        }
    fun showToastL(context: Context, message: String?, type: Int) {
        val mdToast = MDToast.makeText(context, message?.toString(), MDToast.LENGTH_LONG, type)
        mdToast.show()
    }

    fun showProgress(ctx: Context) {

        if (customdialog != null) {
            try {
                if (customdialog!!.isShowing()) {
                    customdialog!!.dismiss()
                    customdialog!!.progress_bar.pauseAnimation()
                }
            } catch (e: Exception) {
            }
        }
        try {
            customdialog = Dialog(ctx, R.style.ActivityDialog);
            customdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customdialog!!.getWindow()!!.setGravity(Gravity.CENTER)
            customdialog!!.setCancelable(false)
            customdialog!!.setContentView(R.layout.custom_progressbar)
            customdialog!!.progress_bar.playAnimation()
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
    fun isconnected(context:Context):Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}