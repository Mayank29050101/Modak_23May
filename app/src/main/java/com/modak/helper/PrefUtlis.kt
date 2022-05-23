package com.modak.helper

import android.content.Context
import com.modak.helper.Prefs.Companion.with

internal object PrefUtils {
    var Is_Languge_Selected = "language_selected"
    var LOGIN_USER_DATA = "login_user_data"
    var SELECTED_COUNTRY_DATA = "selected_country_data"
    var LANGUAGE_DATA = "language_data"
    var USER_TOKEN = "user_token"
    var NEW_USER_LOGIN = "new_user_login"
    var NEW_USER_LOGOUT= "new_user_logout"
    var OTP = "otp"
    var Id="id"
    var City="city"
    var Zipcode="zipcode"
    var Address="address"
    var State="state"
    var New_FirstName = "first_name"
    var Productimage="productimage"
    var New_LastName = "Last_name"
    var Email = "email"
    var Expiries = "expiries"
    var LoginTime = "logintime"
    var Recipe = "recipe"
    var USER_ID = "user_id"
    var CARTID = "CartId"
    var SELECTED_POSITION = "selected_position"
    var FAVORITE_PRODUCT_ID = "favorite_product_id"
    var IS_FAVORITE_PRODUCT = "is_favorite_product"
    var FCM_TOKEN = "fcmToken"
    var IS_MESSGAE_CHAT = "is_messgae_chat"
    var FROM_BACKGROUND = "from_background"
    var Profile_Pic="profile_pic"
    var IS_ITEM_CHANGE = "is_item_change"
    var IS_PRODUCT_FAVORITE = "is_product_favorite"
    var LATITUDE = "latitude"
    var LONGITUDE = "longitude"
    var CHAT_POSITION_CLICK = "chat_position_click"
    var IS_APP_OPEN_FIRST_TIME = "is_app_open_first_time"
    var IS_ADD_ITEM_EDITABLE = "is_add_item_editable"
    var IS_OTP_VERIFIED_ACTIVITY = "is_otp_verified_activity"
    var IS_HOME_FRAGMENT_OPEN = "is_home_fragment_open"
    fun setIsOtpVerifiedActivity(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(IS_OTP_VERIFIED_ACTIVITY, value!!)
    }

    fun getIsOtpVerifiedActivity(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(IS_OTP_VERIFIED_ACTIVITY, false)
    }

    fun setIsHomeFragmentOpen(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(IS_HOME_FRAGMENT_OPEN, value!!)
    }

    fun getIsHomeFragmentOpen(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(IS_HOME_FRAGMENT_OPEN, false)
    }

    fun setIsLangugeSelected(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(Is_Languge_Selected, value!!)
    }

    fun getIsLangugeSelected(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(Is_Languge_Selected, false)
    }

    fun setIsAddItemEditable(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(IS_ADD_ITEM_EDITABLE, value!!)
    }

    fun getIsAddItemEditable(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(IS_ADD_ITEM_EDITABLE, true)
    }

    fun setIsProductFavorite(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(IS_PRODUCT_FAVORITE, value!!)
    }

    fun getIsProductFavorite(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(IS_PRODUCT_FAVORITE, false)
    }

    fun setIsAppOpenFirstTime(ctx: Context?, value: Int) {
        with(ctx!!).save(IS_APP_OPEN_FIRST_TIME, value)
    }

    fun getIsAppOpenFirstTime(ctx: Context?): Int {
        return with(ctx!!).getInt(IS_APP_OPEN_FIRST_TIME, 0)
    }

    fun setLATITUDE(ctx: Context?, value: String?) {
        with(ctx!!).save(LATITUDE, value!!)
    }

    fun getLATITUDE(ctx: Context?): String? {
        return with(ctx!!).getString(LATITUDE, "")
    }

    fun setLONGITUDE(ctx: Context?, value: String?) {
        with(ctx!!).save(LONGITUDE, value!!)
    }

    fun getLONGITUDE(ctx: Context?): String? {
        return with(ctx!!).getString(LONGITUDE, "")
    }

    fun setCARTID(ctx: Context?, value: Int) {
        with(ctx!!).save(CARTID, value)
    }

    fun getCARTID(ctx: Context?): Int {
        return with(ctx!!).getInt(CARTID, 0)
    }

    fun setIsUserLogin(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(NEW_USER_LOGIN, value!!)
    }
    fun getIsUserLogin(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(NEW_USER_LOGIN, false)
    }
    fun setFirstName(ctx: Context?,value:String?){
        with(ctx!!).save(New_FirstName,value!!)
    }
    fun getFirstName(ctx: Context?): String? {
        return with(ctx!!).getString(New_FirstName, "")
    }
    fun setId(ctx: Context?,value:String?){
        with(ctx!!).save(Id,value!!)
    }
    fun getId(ctx: Context?): String? {
        return with(ctx!!).getString(Id, "")
    }
    fun setCityName(ctx: Context?,value: String?){
        with(ctx!!).save(City,value!!)
    }
    fun getCityName(ctx: Context?):String?{
        return  with(ctx!!).getString(City,"")
    }
    fun setZipcode(ctx: Context?,value: String?){
        with(ctx!!).save(Zipcode,value!!)
    }
    fun getZipcode(ctx: Context?):String?{
        return  with(ctx!!).getString(Zipcode,"")
    }
    fun setAddress(ctx: Context?,value: String?){
        with(ctx!!).save(Address,value!!)
    }
    fun getAddress(ctx: Context?):String?{
        return  with(ctx!!).getString(Address,"")
    }
    fun setState(ctx: Context?,value: String?){
        with(ctx!!).save(State,value!!)
    }
    fun getState(ctx: Context?):String?{
        return  with(ctx!!).getString(State,"")
    }
    fun setLastName(ctx: Context?,value:String?){
        with(ctx!!).save(New_LastName,value!!)
    }
    fun getLastName(ctx: Context?): String? {
        return with(ctx!!).getString(New_LastName, "")
    }
    fun setProductImage(ctx: Context?, value: String){
        with(ctx!!).save(Productimage,value!!)
    }
    fun getProductImage (ctx: Context?,value: String?){
        with(ctx!!).save(Productimage,value!!)
    }
    fun setEmail(ctx: Context?,value:String?){
        with(ctx!!).save(Email,value!!)
    }
    fun getEmail(ctx: Context?): String? {
        return with(ctx!!).getString(Email, "")
    }
    fun setExpiries(ctx: Context?,value:String?) {
         with(ctx!!).save(Expiries,value!!)
    }
    fun getExpiries(ctx: Context?):String?{
        return with(ctx!!).getString(Expiries,"")
    }
    fun setLogintime(ctx: Context?,value:String?) {
        with(ctx!!).save(LoginTime,value!!)
    }
    fun getLogintime(ctx: Context?):String?{
        return with(ctx!!).getString(LoginTime,"")
    }
    fun setrecipe(ctx: Context?,value:String?){
        with(ctx!!).save(Recipe,value!!)
    }
    fun getrecipe(ctx: Context?): String? {
        return with(ctx!!).getString(Recipe, "")
    }
    fun setIsItemChange(ctx: Context?, value: Boolean?) {
        with(ctx!!).save(IS_ITEM_CHANGE, value!!)
    }

    fun getIsItemChange(ctx: Context?): Boolean {
        return with(ctx!!).getBoolean(IS_ITEM_CHANGE, false)
    }

    fun setUserToken(ctx: Context?, value: String?) {
        with(ctx!!).save(USER_TOKEN, value!!)
    }

    fun getUserToken(ctx: Context?): String? {
        return with(ctx!!).getString(USER_TOKEN, "")
    }
    fun setProfilePic(ctx: Context?, value: String?) {
        with(ctx!!).save(Profile_Pic, value!!)
    }

    fun getProfilePic(ctx: Context?): String? {
        return with(ctx!!).getString(Profile_Pic, "")
    }

    fun setOTP(ctx: Context?, value: String?) {
        with(ctx!!).save(OTP, value!!)
    }

    fun getOTP(ctx: Context?): String? {
        return with(ctx!!).getString(OTP, "")
    }

    fun setUserId(ctx: Context?, value: Int) {
        with(ctx!!).save(USER_ID, value)
    }

    fun getUserId(ctx: Context?): Int {
        return with(ctx!!).getInt(USER_ID, 0)
    }

    fun setFavoriteProductId(ctx: Context?, value: Int) {
        with(ctx!!).save(FAVORITE_PRODUCT_ID, value)
    }

    fun getFavoriteProductId(ctx: Context?): Int {
        return with(ctx!!).getInt(FAVORITE_PRODUCT_ID, -1)
    }

    fun setIsFavoriteProduct(ctx: Context?, value: Int) {
        with(ctx!!).save(IS_FAVORITE_PRODUCT, value)
    }

    fun getIsFavoriteProduct(ctx: Context?): Int {
        return with(ctx!!).getInt(IS_FAVORITE_PRODUCT, 2)
    }

    fun setSelectedPosition(ctx: Context?, value: Int) {
        with(ctx!!).save(SELECTED_POSITION, value)
    }

    fun getSelectedPosition(ctx: Context?): Int {
        return with(ctx!!).getInt(SELECTED_POSITION, 0)
    }

    fun setFcmToken(ctx: Context?, value: String?) {
        with(ctx!!).save(FCM_TOKEN, value!!)
    }

    fun getFcmToken(ctx: Context?): String? {
        return with(ctx!!).getString(FCM_TOKEN, "")
    }

    fun setIsMessgaeChat(ctx: Context?, value: Int) {
        with(ctx!!).save(IS_MESSGAE_CHAT, value)
    }

    fun getIsMessgaeChat(ctx: Context?): Int {
        return with(ctx!!).getInt(IS_MESSGAE_CHAT, 0)
    }

    fun setFromBackground(ctx: Context?, value: Int) {
        with(ctx!!).save(FROM_BACKGROUND, value)
    }

    fun getFromBackground(ctx: Context?): Int {
        return with(ctx!!).getInt(FROM_BACKGROUND, 0)
    }


    fun setChatPositionClick(ctx: Context?, value: Int) {
        with(ctx!!).save(CHAT_POSITION_CLICK, value)
    }

    fun getChatPositionClick(ctx: Context?): Int {
        return with(ctx!!).getInt(CHAT_POSITION_CLICK, 0)
    }
}