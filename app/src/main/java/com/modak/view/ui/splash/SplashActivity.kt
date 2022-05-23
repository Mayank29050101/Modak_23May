package com.modak.view.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.modak.R
import com.modak.helper.PrefUtils
import com.modak.view.ui.dashboard.DashboardActivity
import com.modak.view.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*


@Suppress("DEPRECTION")

class SplashActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var mAuthListner: FirebaseAuth.AuthStateListener? = null
    private var firebaseAuth: FirebaseAuth? = null
    var mAuthListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val animation = AnimationUtils.loadAnimation(applicationContext,R.anim.blink)
        app_logo.startAnimation(animation)
            Handler().postDelayed({
                if (PrefUtils.getIsUserLogin(this)  ){
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }, 3000) // 1000 is the delayed time in milliseconds.

        firebaseAuth = FirebaseAuth.getInstance()

        mAuthListener = AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        firebaseAuth = FirebaseAuth.getInstance()

    }
}





