package com.modak.view.ui.dashboard



import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.*
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.modak.*
import com.modak.helper.DBHelper
import com.modak.helper.PrefUtils
import com.modak.helper.function
import com.modak.model.RequestPojo.LogoutRequest
import com.modak.model.ResponsePojo.LogoutResponse
import com.modak.repositories.APIClient
import com.modak.view.*
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.drawer.*
import com.modak.view.ui.fragments.FilterFragment
import com.modak.view.ui.header.EditProfileActivity
import com.modak.view.ui.header.ModakMoneyActivity
import com.modak.view.ui.header.MyOrderActivity
import com.modak.view.ui.header.ReviewActivity
import com.modak.view.ui.home.HomeFragment
import com.modak.view.ui.navigation.DeliveryFragment
import com.modak.view.ui.navigation.DineinFragment
import com.modak.view.ui.navigation.FavoriteFragment
import com.modak.view.ui.navigation.TakeoutFragment
import com.modak.view.ui.payment_method.PaymentMethodActivity
import com.modak.view.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


open class DashboardActivity : AppCompatActivity() {
    var dbHelper= DBHelper(this,null)
    var timer = Timer()
    var doubleBackToExitPressedOnce: Boolean = false

    private lateinit var actionBarToggle: ActionBarDrawerToggle
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.red, theme)
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar_m)
        setSupportActionBar(toolbar)
        loadFragment(HomeFragment())
        //dbHelper.getData(1)
        bottom_navigation.itemIconTintList = null
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.setDrawerListener(actionBarToggle)
        ivLocation.setOnClickListener(View.OnClickListener {

            val browser= Uri.parse("http://maps.google.com/maps?")
            val intent=Intent(Intent.ACTION_VIEW,browser)
            startActivity(intent)

        })
        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()
        //for My order activity//
        nav_View_.getHeaderView(0)
        val txt:TextView=nav_View_.getHeaderView(0).findViewById(R.id.txt_profile) as TextView
        //txt.text="Mayank"
        txt.text=PrefUtils.getFirstName(this)+" "+PrefUtils.getLastName(this).toString()
        //for order activity//
        val order:View=nav_View_.getHeaderView(0).findViewById(R.id.my_orders)
        order.setOnClickListener(View.OnClickListener {
            function.showToast(this,"MyOrders", MDToast.TYPE_SUCCESS)
            val intent=Intent(this, MyOrderActivity::class.java)
            startActivity(intent)
            btn_order.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))
        })
        //for modak money activity//
        val modak_money:View=nav_View_.getHeaderView(0).findViewById(R.id.modak_money)
        modak_money.setOnClickListener(View.OnClickListener {
            function.showToast(this,"Modak Money", MDToast.TYPE_SUCCESS)
            val intent=Intent(this, ModakMoneyActivity::class.java)
            startActivity(intent)
            btn_money.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))


        })
        //for review activity//
        val review:View=nav_View_.getHeaderView(0).findViewById(R.id.reviews)
        review.setOnClickListener(View.OnClickListener {
            function.showToast(this,"Reviews", MDToast.TYPE_SUCCESS)
            val intent=Intent(this, ReviewActivity::class.java)
            startActivity(intent)
            btn_review.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)))

        })
        //for edit profile activity//
        val edit_profile:ImageView=nav_View_.getHeaderView(0).findViewById(R.id.edt_profile_arrow)
        edit_profile.setOnClickListener(View.OnClickListener {
            function.showToast(this,"Edit Profile", MDToast.TYPE_SUCCESS)
            val intent=Intent(this, EditProfileActivity::class.java)
            startActivity(intent)

        })

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment
            when (item.itemId) {
                R.id.item_fav -> {
                    txt_city.setText("Favorite")
                    toolbar?.setTitle("Favorite")
                    ivLocation.setImageResource(R.drawable.ic_baseline_favorite_24)
                    fragment = FavoriteFragment()
                    loadFragment(fragment)
                }
                R.id.item_delivery -> {
                    txt_city.setText("Delivery")
                    toolbar?.setTitle("Delivery")
                    ivLocation.setImageResource(R.drawable.truck)
                    fragment = DeliveryFragment()
                    loadFragment(fragment)
                }
                R.id.item_take -> {
                    txt_city.setText("Take Out")
                    toolbar?.setTitle("Take Out")
                    //ivLocation.visibility = View.GONE
                    ivLocation.setImageResource(R.drawable.take_away_)
                    fragment = TakeoutFragment()
                    loadFragment(fragment)
                }
                R.id.item_dine -> {
                    txt_city.setText("Dein In")
                    toolbar?.setTitle("Dein In")
                    //ivLocation.visibility = View.GONE
                    ivLocation.setImageResource(R.drawable.ic_baseline_local_dining_24)
                    fragment = DineinFragment()
                    loadFragment(fragment)
                }
            }
            true
        }

        nav_View_.setCheckedItem(R.id.nav_home_)
        nav_View_.setNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {

                R.id.nav_home_ -> {
                    txt_city.setText("Home")
                    fragment = HomeFragment()
                    loadFragment(fragment)
                }
                R.id.nav_profile -> {
                    txt_city.setText("My Profile")
                    ivLocation.visibility = View.GONE
                    fragment = MyProfileFragment()
                    loadFragment(fragment)
                }
                R.id.nav_location -> {
                    val nav_loaction = Intent(this@DashboardActivity, LocationActivity::class.java)
                    startActivity(nav_loaction)
                }
                R.id.nav_payment -> {
                    val nav_payment =
                        Intent(this@DashboardActivity, PaymentMethodActivity::class.java)
                    startActivity(nav_payment)
                }
                R.id.nav_notification -> {
                    txt_city.setText("Notification")
                    ivLocation.visibility = View.GONE
                    fragment = NotificationFragment()
                    loadFragment(fragment)
                }
                R.id.nav_meal -> {
                    txt_city.setText("Free Meal")
                    ivLocation.visibility = View.GONE
                    fragment = FreeMealFragment()
                    loadFragment(fragment)
                }
                R.id.nav_offer ->{
                    txt_city.setText("Offers")
                    ivLocation.visibility=View.GONE
                    fragment= OfferFragment()
                    loadFragment(fragment)
                }
                R.id.nav_settings -> {
                    txt_city.setText("Settings")
                    ivLocation.visibility = View.GONE
                    fragment = SettingFragment()
                    loadFragment(fragment)
                }
                R.id.nav_help -> {
                    txt_city.setText("Help")
                    ivLocation.visibility = View.GONE
                    fragment = HelpFragment()
                    loadFragment(fragment)
                }
                R.id.nav_logout -> {
                    if (!PrefUtils.getIsUserLogin(this)) {
                        val intent=Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        LogoutDialog()
                    }
                }
                else -> finish()
            }
            nav_View_.setCheckedItem(item)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


    }

    private fun LogoutDialog() {
        val logoutDialog = Dialog(this, R.style.BottomDialog)
        val contentView: View = LayoutInflater.from(this).inflate(R.layout.logout_dailog, null)
        logoutDialog.setContentView(contentView)
        logoutDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = contentView.layoutParams
        layoutParams.width = resources.displayMetrics.widthPixels
        contentView.layoutParams = layoutParams
        logoutDialog.window!!.setGravity(Gravity.CENTER)
        logoutDialog.setCanceledOnTouchOutside(false)
        logoutDialog.window!!.setWindowAnimations(R.style.WindowAnimationTransition)
        logoutDialog.show()
        val no:TextView=logoutDialog.findViewById(R.id.no_txt)
        no.setOnClickListener(View.OnClickListener {
            no.setTextColor(resources.getColor(R.color.red))
            logoutDialog.dismiss()
        })
        val yes:TextView=logoutDialog.findViewById(R.id.yes_txt)
        yes.setOnClickListener(View.OnClickListener {
            yes.setTextColor(resources.getColor(R.color.red))
            var logoutRequest:LogoutRequest= LogoutRequest()
            var logoutResponse:LogoutResponse= LogoutResponse()
            logoutRequest.email=PrefUtils.getEmail(this@DashboardActivity).toString()
            APIClient.getService().Logout("Bearer " + PrefUtils.getUserToken(this),logoutRequest).enqueue(object :Callback<LogoutResponse>{
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    if (response.body() !=null){
                        function.showToast(this@DashboardActivity,"Logged out :- ${response.body()!!.email}",MDToast.TYPE_SUCCESS)
                        PrefUtils.setIsUserLogin(this@DashboardActivity, false)
                        val intent=Intent(this@DashboardActivity,com.modak.view.ui.login.LoginActivity::class.java)
                        startActivity(intent)
                        // PrefUtils.setIsUserLogout(this@DashboardActivity,true)
                    }
                }
                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Log.d("Logout",t.localizedMessage.toString())
                }
            })
        })
    }

    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            fun doubleTapOnBackPress() {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    finish()
                    finishAffinity()
                    return
                }
                this.doubleBackToExitPressedOnce = true
                function.showToast(this,"Press Back twice to exit the application",MDToast.TYPE_INFO)
                Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
            }
        }
    }


    private fun loadFragment(fragment: Fragment) {
        // load fragment

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
            .replace(R.id.frameContainer, fragment)
            .commit()
    }


    //    setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val fragment:Fragment
        return when (item.itemId) {

            R.id.option_search -> {
                actionBarToggle = ActionBarDrawerToggle(this, drawerLayout,toolbar_m, R.string.open, R.string.close)
                fragment = SearchFragment()
                loadFragment(fragment)
                true
            }
            R.id.option_filter -> {
                actionBarToggle =
                    ActionBarDrawerToggle(this, drawerLayout,toolbar_m, R.string.open, R.string.close)
                drawerLayout.setDrawerListener(actionBarToggle)
                fragment = FilterFragment()
                loadFragment(fragment)
                function.showToast(this,"Filter", MDToast.TYPE_SUCCESS)
                true
            }
            R.id.option_cart -> {
                val cart=Intent(this,BasketActivity::class.java)
                startActivity(cart)
                true
            }
            else -> {
                false
            }
        }
    }

}


