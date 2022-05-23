package com.modak.model.ResponsePojo

open class LoginResponse:BaseResponse(){
    var access_token: String?=""
    var device_info: String?=""
    var email: String?=""
    var expiries: String?=""
    var first_name: String?=""
    var ip_address: String?=""
    var last_name: String?=""
    var login_time: String?=""
    var timezone: String?=""
}

