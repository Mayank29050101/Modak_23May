package com.modak.model.ResponsePojo

open class SignupResponse:BaseResponse(){

   var records: Int=0
   var response: List<SignupDataResponse> = arrayListOf()
}