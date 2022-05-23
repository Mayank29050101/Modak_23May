package com.modak.repositories

object APIConstant {

    private val BASEURL = String.format("%s", "https://staging.webmynehost.com/")
    fun getBaseHost(): String {
        return BASEURL
    }
    const val Signup="modak/pages/users/create.php"
    const val Signupget="modak/pages/users/get.php"
    const val Login="modak/pages/login/loginWithEmail.php"
    const val Logout="modak/pages/login/logout.php"
    const val Product="modak/pages/product/create.php"
    const val getproduct="modak/pages/product/get.php"
    const val state="modak/pages/state/get.php"


    const val status_code="200"
}