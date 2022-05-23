package com.modak.view.ui.category

class NotifyModal(img_notify:Int,txt_n:String?,txt_date:String?) {
    private var img_notify:Int = 0
    private lateinit var txt_n: String
    private lateinit var txt_date: String
    init {
        this.img_notify=img_notify
        this.txt_n=txt_n!!
        this.txt_date=txt_date!!
    }
    fun getimg():Int{
        return img_notify
    }
    fun getdate():String{
        return txt_date
    }
    fun gettxt_n():String{
        return txt_n
    }
}