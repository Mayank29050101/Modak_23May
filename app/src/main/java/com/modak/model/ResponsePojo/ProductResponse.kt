package com.modak.model.ResponsePojo

open class ProductResponse:BaseResponse(){

    var records: Int?=0
    var response: List<ProductDataResponse> = arrayListOf()

}