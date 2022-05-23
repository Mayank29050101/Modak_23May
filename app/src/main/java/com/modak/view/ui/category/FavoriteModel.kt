package com.modak.view.ui.category
class FavoriteModel(txt_favchef:String?,txt_favrate:String?,txt_rava:String?,txt_Cuisines:String?,txt_Cuisines_detail:String?,txt_qty:String?,fav_add:Int,Img_fav:Int,Img_smile:Int,fav_profile:Int) {
    private var txt_favchef: String
    private var txt_favrate:String
    private var txt_Cuisines: String
    private var txt_rava: String
    private var txt_Cuisines_detail: String
    private var txt_qty: String
    private var fav_add:Int=0
    private var Img_fav:Int=0
    private var Img_smile:Int=0
    private var fav_profile:Int=0
    init {
        this.txt_favchef = txt_favchef!!
        this.txt_favrate=txt_favrate!!
        this.txt_Cuisines=txt_Cuisines!!
        this.txt_Cuisines_detail=txt_Cuisines_detail!!
        this.txt_rava=txt_rava!!
        this.txt_qty=txt_qty!!
        this.fav_add=fav_add
        this.Img_fav=Img_fav
        this.Img_smile=Img_smile
        this.fav_profile=fav_profile

    }
    fun getfavchef():String?{
        return txt_favchef
    }
    fun getfavrate():String?{
        return txt_favrate
    }
    fun getcuisines():String?{
        return txt_Cuisines
    }
    fun getcuisinesdetail():String?{
        return txt_Cuisines_detail
    }
    fun getrava():String?{
        return txt_rava
    }
    fun getqty():String?{
        return txt_qty
    }
    fun getfavadd():Int{
        return fav_add
    }
    fun getImg_fav():Int{
        return Img_fav
    }
    fun getImg_smile():Int{
        return Img_smile
    }
    fun getfavprofile():Int{
        return fav_profile
    }

}