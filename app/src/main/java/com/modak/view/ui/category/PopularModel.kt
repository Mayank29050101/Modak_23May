package com.modak.view.ui.category



class PopularModel(popular_name:String?,popular_detail:String?,rate_p:String?,img_popular:Int,rating_p:Int?) {
    private var popular_name: String
    private var popular_detail: String
    private var rate_p: String
    private var img_popular:Int
    private var rating_p:Int
    init {
        this.popular_name = popular_name!!
        this.popular_detail = popular_detail!!
        this.rate_p = rate_p!!
        this.img_popular=img_popular!!
        this.rating_p=rating_p!!
    }
    fun getpopular_name(): String? {
        return popular_name
    }
    fun getpopular_detail(): String? {
        return popular_detail
    }
    fun getrate_p(): String?{
        return rate_p
    }
    fun getimg_popular(): Int{
        return img_popular
    }
    fun getrating_p():Int{
        return rating_p
    }
}