package com.modak.helper



open class SharedPrefManager {


    companion object {
        private var FAVORITE = "favorite"
        private var LANGUAGEVALUE = "languagevalue"
        private var ORDER_STATUS = "order_status"
        private var PAYMENT_TYPE = "payment_type"
        private var DELIVERY_TYPE = "delivery_type"
        private var ITEM_STATUS = "item_status"
        private var USER_TYPE = "user_type"
        private var CONTACT_REASON = "contact_reason"
        private var COUNTRY_LIST_FROM_MASTER = "country_list_from_master"



        //fun setOrderStatus(activity: BaseActivity, orderStatus: OrderStatus) {
//            val type = object : TypeToken<OrderStatus>() {}.type
//            with(
//                activity.getSharedPreferences(
//                    activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//                )
//                    .edit()
//            ) {
//                putString(ORDER_STATUS, com.google.gson.Gson().toJson(orderStatus, type))
//                apply()
//            }
//        }
//
//        fun getOrderStatus(activity: BaseActivity): OrderStatus? {
//            val sharedPreferences = activity.getSharedPreferences(
//                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//            )
//            var statusList = sharedPreferences.getString(ORDER_STATUS, "")
//            val type = object : TypeToken<OrderStatus>() {}.type
//            if (statusList?.trim()!!.length > 0) {
//                return Gson().fromJson(statusList, type)
//            } else {
//                return null
//            }
//        }
//
//        fun setPaymentType(activity: BaseActivity, payment: Payment) {
//            val type = object : TypeToken<Payment>() {}.type
//            with(
//                activity.getSharedPreferences(
//                    activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//                )
//                    .edit()
//            ) {
//                putString(PAYMENT_TYPE, com.google.gson.Gson().toJson(payment, type))
//                apply()
//            }
//        }
//
//        fun getPaymentType(activity: BaseActivity): Payment? {
//            val sharedPreferences = activity.getSharedPreferences(
//                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//            )
//            var paymentList = sharedPreferences.getString(PAYMENT_TYPE, "")
//            val type = object : TypeToken<Payment>() {}.type
//            if (paymentList?.trim()!!.length > 0) {
//                return Gson().fromJson(paymentList, type)
//            } else {
//                return null
//            }
//        }
//
//        fun setDeliveryType(activity: BaseActivity, delivery: Delivery) {
//            val type = object : TypeToken<Delivery>() {}.type
//            with(
//                activity.getSharedPreferences(
//                    activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//                )
//                    .edit()
//            ) {
//                putString(DELIVERY_TYPE, com.google.gson.Gson().toJson(delivery, type))
//                apply()
//            }
//        }
//
//        fun getDeliveryType(activity: BaseActivity): Delivery? {
//            val sharedPreferences = activity.getSharedPreferences(
//                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//            )
//            var deliveryList = sharedPreferences.getString(DELIVERY_TYPE, "")
//            val type = object : TypeToken<Delivery>() {}.type
//            if (deliveryList?.trim()!!.length > 0) {
//                return Gson().fromJson(deliveryList, type)
//            } else {
//                return null
//            }
//        }
//        fun setItemStatus(activity: BaseActivity, itemStatus: ItemStatus) {
//            val type = object : TypeToken<ItemStatus>() {}.type
//            with(
//                activity.getSharedPreferences(
//                    activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//                )
//                    .edit()
//            ) {
//                putString(ITEM_STATUS, com.google.gson.Gson().toJson(itemStatus, type))
//                apply()
//            }
//        }
//
//        fun getItemStatus(activity: BaseActivity): ItemStatus? {
//            val sharedPreferences = activity.getSharedPreferences(
//                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//            )
//            var itemStatus = sharedPreferences.getString(ITEM_STATUS, "")
//            val type = object : TypeToken<ItemStatus>() {}.type
//            if (itemStatus?.trim()!!.length > 0) {
//                return Gson().fromJson(itemStatus, type)
//            } else {
//                return null
//            }
//        }
//
//        fun setUserType(activity: BaseActivity, userType: UserType) {
//            val type = object : TypeToken<UserType>() {}.type
//            with(
//                activity.getSharedPreferences(
//                    activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//                )
//                    .edit()
//            ) {
//                putString(USER_TYPE, com.google.gson.Gson().toJson(userType, type))
//                apply()
//            }
//        }
//
//        fun getUserType(activity: BaseActivity): UserType? {
//            val sharedPreferences = activity.getSharedPreferences(
//                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//            )
//            var userType = sharedPreferences.getString(USER_TYPE, "")
//            val type = object : TypeToken<UserType>() {}.type
//            if (userType?.trim()!!.length > 0) {
//                return Gson().fromJson(userType, type)
//            } else {
//                return null
//            }
//        }
//
//
//
//
//        fun setCountryListToMaster(activity: BaseActivity, countryList: Country) {
//            val type = object : TypeToken<Country>() {}.type
//            with(
//                activity.getSharedPreferences(
//                    activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//                )
//                    .edit()
//            ) {
//                putString(COUNTRY_LIST_FROM_MASTER, com.google.gson.Gson().toJson(countryList, type))
//                apply()
//            }
//        }
//
//        fun getCountryListFromMaster(activity: BaseActivity): Country? {
//            val sharedPreferences = activity.getSharedPreferences(
//                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE
//            )
//            var countryList = sharedPreferences.getString(COUNTRY_LIST_FROM_MASTER, "")
//            val type = object : TypeToken<Country>() {}.type
//            if (countryList?.trim()!!.length > 0) {
//                return Gson().fromJson(countryList, type)
//            } else {
//                return null
//            }
//        }
    }
}