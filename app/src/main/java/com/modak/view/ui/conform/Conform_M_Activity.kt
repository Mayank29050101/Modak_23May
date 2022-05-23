package com.modak.view.ui.conform

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.modak.R
import com.modak.view.ui.dashboard.DashboardActivity
import com.modak.view.ui.drawer.*
import com.modak.view.ui.home.HomeFragment
import com.modak.view.ui.payment_method.PaymentMethodActivity
import kotlinx.android.synthetic.main.activity_conform_mactivity.*
import kotlinx.android.synthetic.main.toolbar.*

class Conform_M_Activity : AppCompatActivity() {
    private lateinit var actionBarToggle_c: ActionBarDrawerToggle
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conform_mactivity)
        window.statusBarColor = resources.getColor(R.color.red, theme)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_c_m).apply { setSupportActionBar(this) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        loadFragment(ConfirmationFragment())
        actionBarToggle_c =
            ActionBarDrawerToggle(this, drawerLayout_c, toolbar, R.string.open, R.string.close)
        drawerLayout_c.setDrawerListener(actionBarToggle_c)
        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle_c.syncState()
        //for My order activity//

        nav_View_c.setCheckedItem(R.id.nav_home_)
        nav_View_c.setNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {

                R.id.nav_home_ -> {
                    txt_city.setText("Home")
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    //Toast.makeText(this, "Home ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_profile -> {
                    txt_city.setText("My Profile")
                    ivLocation.visibility = View.GONE
                    fragment = MyProfileFragment()
                    loadFragment(fragment)
                    // Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_location -> {
                    val nav_loaction = Intent(this, LocationActivity::class.java)
                    startActivity(nav_loaction)
                    //Toast.makeText(this, "Location ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_payment -> {
                    val nav_payment =
                        Intent(this, PaymentMethodActivity::class.java)
                    startActivity(nav_payment)
                    //Toast.makeText(this, "Payments ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_notification -> {
                    txt_city.setText("Notification")
                    ivLocation.visibility = View.GONE
                    fragment = NotificationFragment()
                    loadFragment(fragment)
                    // Toast.makeText(this, "Notification ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_meal -> {
                    txt_city.setText("Free Meal")
                    ivLocation.visibility = View.GONE
                    fragment = FreeMealFragment()
                    loadFragment(fragment)
                    //Toast.makeText(this, "My Meals ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_offer ->{
                    txt_city.setText("Offers")
                    ivLocation.visibility= View.GONE
                    fragment= OfferFragment()
                    loadFragment(fragment)
                }
                R.id.nav_settings -> {
                    txt_city.setText("Settings")
                    ivLocation.visibility = View.GONE
                    fragment = SettingFragment()
                    loadFragment(fragment)
                    //Toast.makeText(this, "Opening Settings ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_help -> {
                    txt_city.setText("Help")
                    ivLocation.visibility = View.GONE
                    fragment = HelpFragment()
                    loadFragment(fragment)
                    // Toast.makeText(this, "Help ", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_logout -> {
                    finishAffinity()
                }
                else -> finish()
            }
            nav_View_c.setCheckedItem(item)
            drawerLayout_c.closeDrawer(GravityCompat.START)
            true
        }


    }

    override fun onBackPressed() {
        if (this.drawerLayout_c.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout_c.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer_c, fragment)
            .commit()
    }

    //    setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.conform_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val fragment: Fragment
        return when (item.itemId) {

            R.id.home_conform -> {
                val home=Intent(this, DashboardActivity::class.java)
                startActivity(home)
                true
            }
            else -> {
                false
            }
        }
    }

}