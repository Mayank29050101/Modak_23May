package com.modak.view.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.modak.R
import com.modak.databinding.FragmentHomeBinding
import com.modak.helper.PrefUtils
import com.modak.helper.function
import com.modak.model.ResponsePojo.ProductDataResponse
import com.modak.model.ResponsePojo.ProductResponse
import com.modak.repositories.APIClient
import com.modak.view.ui.adapter.FoodAdapter
import com.modak.view.ui.adapter.PopularAdapter
import com.modak.view.ui.category.FoodModel
import com.modak.view.ui.category.PopularModel
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.fragments.ShowAllFragment
import com.modak.view.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_homepage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val foodList = ArrayList<FoodModel>()
    private val popularList=ArrayList<PopularModel>()
    val adapter:FoodAdapter? = null
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var favoriteList:List<ProductDataResponse> = arrayListOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        food_list()
        popular_List()
        adds()
        txt_showall.setOnClickListener(View.OnClickListener {
            val fragment: Fragment = ShowAllFragment()
            val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameContainer, fragment)
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })
//        refreshlayout.setOnRefreshListener {
//            Toast.makeText(requireContext(), "Refreshing", Toast.LENGTH_SHORT).show()
//            food_list()
//            popular_List()
//            refreshlayout.isRefreshing=false
//        }
    }

    private fun adds() {
        tabLayout_ads!!.addTab(tabLayout_ads!!.newTab().setIcon(R.drawable.tab_selector))
        tabLayout_ads!!.addTab(tabLayout_ads!!.newTab().setIcon(R.drawable.tab_selector))
        tabLayout_ads!!.addTab(tabLayout_ads!!.newTab().setIcon(R.drawable.tab_selector))
        tabLayout_ads!!.addTab(tabLayout_ads!!.newTab().setIcon(R.drawable.tab_selector))
        tabLayout_ads!!.addTab(tabLayout_ads!!.newTab().setIcon(R.drawable.tab_selector))
        tabLayout_ads!!.addTab(tabLayout_ads!!.newTab().setIcon(R.drawable.tab_selector))
        tabLayout_ads!!.tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_CENTER
        val adapter = AdsAdapter(requireContext(), activity?.supportFragmentManager!!, tabLayout_ads!!.tabCount)
        viewPager_ads.adapter = adapter
        tabLayout_ads.setupWithViewPager(viewPager_ads)
        viewPager_ads!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout_ads))
        tabLayout_ads!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("Range")
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_ads!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }
    private fun food_list() {
        recyclerview_shopbycategory.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val food_adapter= FoodAdapter(requireContext(),foodList)
        recyclerview_shopbycategory.adapter=food_adapter
        food()
    }

    private fun popular_List() {
        function.showProgress(requireContext())
        APIClient.getService().getProduct("Bearer " + PrefUtils.getUserToken(requireContext())).enqueue(object :
            Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                function.closeProgress()
                if (response.body()!=null){
                    favoriteList= response.body()!!.response
                    recyclerview_popular.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    val popular_adapter= PopularAdapter(requireContext(),favoriteList)
                    recyclerview_popular.adapter=popular_adapter
                }
                else if (response.code() == 401) {
                    val intent =
                        Intent(requireContext(), LoginActivity::class.java)
                    function.showToast(requireContext(),"Token expired", MDToast.TYPE_ERROR)
                    startActivity(intent)
                    PrefUtils.setIsUserLogin(context,false)
                }
                else
                {

                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("getProduct",t.localizedMessage.toString())
            }
        })

    }
    private fun food() {
        var food=FoodModel("Chicken Briyani ",R.drawable.banner_scroll)
        foodList.add(food)
        food=FoodModel(" Dosa ",R.drawable.bg_food_detail)
        foodList.add(food)
        food=FoodModel(" Briyani ",R.drawable.banner_scroll)
        foodList.add(food)
        food=FoodModel(" Dosa ",R.drawable.bg_food_detail)
        foodList.add(food)
        food=FoodModel(" Briyani ",R.drawable.banner_scroll)
        foodList.add(food)
    }
}