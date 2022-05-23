package com.modak.repositories

import com.modak.model.RequestPojo.LoginRequest
import com.modak.model.RequestPojo.LogoutRequest
import com.modak.model.RequestPojo.SignupRequest
import com.modak.model.ResponsePojo.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @POST(APIConstant.Signup)
    fun postUser(@Body signupRequest: SignupRequest):Call<SignupResponse>

    @POST(APIConstant.Login)
    fun Login(@Body loginRequest: LoginRequest):Call<LoginResponse>

    @POST(APIConstant.Logout)
    fun Logout(@Header("Authorization") UserToken: String,@Body logoutRequest: LogoutRequest):Call<LogoutResponse>

    @GET(APIConstant.Signupget)
    fun getSignup(@Header("Authorization") UserToken: String,@Query ("id") id:String):Call<SignupResponse>
    @Multipart
    @POST(APIConstant.Product)
    fun AddProduct(
        @Header("Authorization") UserToken: String,
        @Part("Name") Name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("recipe") recipe: RequestBody,
        @Part photo: MutableList<MultipartBody.Part>,
        @Part("Spicy") Spicy: RequestBody,
    ): Call<ProductResponse>

    @GET(APIConstant.getproduct)
    fun getProduct( @Header("Authorization") UserToken: String):Call<ProductResponse>

    @GET(APIConstant.getproduct)
    fun getProductid(@Header("Authorization") UserToken: String, @Query ("id") id: Int?):Call<ProductResponse>

    @GET(APIConstant.state)
    fun getState():Call<StateResponse>
}