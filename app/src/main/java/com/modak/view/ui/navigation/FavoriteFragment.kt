package com.modak.view.ui.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.modak.R
import com.modak.helper.PrefUtils
import com.modak.helper.function
import com.modak.model.ResponsePojo.ProductDataResponse
import com.modak.model.ResponsePojo.ProductResponse
import com.modak.repositories.APIClient
import com.modak.view.ui.adapter.FavoruteAdapter
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteFragment : Fragment() {
    private var favoriteList:List<ProductDataResponse> = arrayListOf()
    private var productResponse:ProductResponse= ProductResponse()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!function.isconnected(requireContext())){
            function.showToast(requireContext(),"Please turn on your Internet",MDToast.TYPE_WARNING)
            return
        }

        ProductApi()
    }

    private fun ProductApi() {
        function.showProgress(requireContext())
        APIClient.getService().getProduct("Bearer " + PrefUtils.getUserToken(requireContext())).enqueue(object :Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                function.closeProgress()
                if (response.body()!=null){
                    favoriteList= response.body()!!.response
                    recyclerview_favorite.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    val favorite_adapter= FavoruteAdapter(requireContext(),favoriteList)
                    recyclerview_favorite.adapter=favorite_adapter
                }
                else if (response.code() == 401) {
                    val intent =
                        Intent(requireContext(), LoginActivity::class.java)
                    function.showToast(requireContext(),"Token expired",MDToast.TYPE_ERROR)
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

    override fun onResume() {
        super.onResume()
        ProductApi()
    }

    override fun onPause() {
        super.onPause()
        ProductApi()
    }

    override fun onStart() {
        super.onStart()
        ProductApi()
    }

//    private fun favorite() {
//       var favorite=FavoriteModel("Chef Dazzy","$35.00","Veg.Rava masala dosa,dahi idli","Cuisine :","South Indian","Qty 3",R.drawable.add,R.drawable.bg_food_detail,R.drawable.smile,R.drawable.dp1)
//       favoriteList.add(favorite)
//        favorite=FavoriteModel("Chef Rashed","$20.00","Veg.Rava masala dosa,dahi idli","Cuisine :","South Indian","Qty 3",R.drawable.add,R.drawable.banner_scroll,R.drawable.smile,R.drawable.dp1)
//        favoriteList.add(favorite)
//        favorite=FavoriteModel("Chef Rashed khan","$25.00","Veg.Rava masala dosa,dahi idli","Cuisine :","South Indian","Qty 3",R.drawable.add,R.drawable.banner_scroll,R.drawable.smile,R.drawable.dp1)
//        favoriteList.add(favorite)
//    }


}