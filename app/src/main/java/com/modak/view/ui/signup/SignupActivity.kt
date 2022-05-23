package com.modak.view.ui.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.modak.R
import com.modak.helper.PrefUtils
import com.modak.helper.function
import com.modak.model.RequestPojo.SignupRequest
import com.modak.model.ResponsePojo.SignupResponse
import com.modak.model.ResponsePojo.StateDataResponse
import com.modak.model.ResponsePojo.StateResponse
import com.modak.repositories.APIClient
import com.modak.view.ui.adapter.StateCodeSpinAdapter
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    var signupreq: SignupRequest = SignupRequest()
    var signupres: SignupResponse = SignupResponse()
    val stateresponse: List<StateDataResponse> = arrayListOf()
    var StateCode: String = ""
    var StateCodeList: MutableList<StateDataResponse> = mutableListOf()
    var StateCodeSpinAdapter: StateCodeSpinAdapter? = null
    var StateCodeUser: String = ""

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        initViews()
        val button: Button = this.findViewById(R.id.create)
        button.setOnClickListener {
            if (checkDataEntered()) {
                //SpinnerAction()
                signupreq.fname = edtFirstname.text.toString().trim()
                signupreq.lname = edtLastname.text.toString().trim()
                signupreq.email = edtEmail_l.text.toString().trim()
                signupreq.username = username.text.toString().trim()
                signupreq.address = edtApartment.text.toString().trim()
                signupreq.phone = edtPhoneNumber.text.toString().trim()
                signupreq.pincode = zipcode.text.toString().trim()
                signupreq.city = edtCity.text.toString().trim()
                signupreq.state_id = StateCode.toString().trim()
                signupreq.password = edtpassword_s.text.toString().trim()
                APIClient.getService().postUser(signupreq)
                    .enqueue(object : Callback<SignupResponse> {
                        override fun onResponse(
                            call: Call<SignupResponse>,
                            response: Response<SignupResponse>
                        ) {
                            if (response.body() != null) {

                                function.showToast(
                                    this@SignupActivity, response.body()!!.message.toString(),
                                    MDToast.TYPE_SUCCESS
                                )
                                PrefUtils.setId(
                                    this@SignupActivity,
                                    response.body()!!.response.get(0).id.toString()
                                )
                                PrefUtils.setCityName(
                                    this@SignupActivity,
                                    response.body()!!.response.get(0).city
                                )
                                PrefUtils.setState(
                                    this@SignupActivity,
                                    response.body()!!.response.get(0).state_name
                                )
                                PrefUtils.setAddress(
                                    this@SignupActivity,
                                    response.body()!!.response.get(0).address
                                )
                                PrefUtils.setZipcode(
                                    this@SignupActivity,
                                    response.body()!!.response.get(0).pincode
                                )
                                if (response.body()!!.status_code!!.toString() == "200") {
                                    val intent =
                                        Intent(this@SignupActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                }

                            }
                        }

                        override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                            Log.d("postUser", t.localizedMessage.toString())
                        }
                    })
            }
        }
    }

    private fun initViews() {
        DropDownState()
        ContryState()
    }

    private fun ContryState() {

        APIClient.getService().getState()
            .enqueue(object : Callback<StateResponse> {
                override fun onFailure(
                    call: Call<StateResponse>,
                    t: Throwable
                ) {
                    Log.e("GetContryCode", t.message!!)
                }

                override fun onResponse(
                    call: Call<StateResponse>,
                    response: Response<StateResponse>
                ) {
                    StateCodeList = response.body()!!.response as MutableList<StateDataResponse>
                    if (response.body() != null) {


                        StateCodeSpinAdapter!!.setCountryCodeList(StateCodeList)
                    }
                }
            })
    }

    private fun DropDownState() {
        StateCodeSpinAdapter =
            StateCodeSpinAdapter(
                this, R.layout.spinner_item_dropdown,
                R.id.txtItemSpinner,
                StateCodeList
            )

        spinnerCountryCode.adapter = StateCodeSpinAdapter
        spinnerCountryCode.setSelection(0)

        spinnerCountryCode.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View, position: Int,
                l: Long
            ) {
                StateCode = StateCodeList.get(position).name!!
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        })
    }

//    private fun SpinnerAction() {
//        APIClient.getService().getState().enqueue(object :Callback<StateResponse>{
//            override fun onResponse(call: Call<StateResponse>, response: Response<StateResponse>) {
//                if (response.body()!=null){
//                    var stateadpater=StateAdapter(this@SignupActivity,R.layout.spinner_item_dropdown,
//                        R.id.txtItemSpinner,
//                        stateresponse
//                    )
//                    statespinner.adapter = stateadpater
//                    statespinner.setSelection(0)
//
//                    statespinner.setOnItemSelectedListener(object :
//                        AdapterView.OnItemSelectedListener {
//                        override fun onItemSelected(
//                            adapterView: AdapterView<*>, view: View, position: Int,
//                            l: Long
//                        ) {
//                            StateCode = stateresponse.get(position).id!!.toString()
//                        }
//
//                        override fun onNothingSelected(adapterView: AdapterView<*>) {}
//                    })
//                }
//            }
//
//            override fun onFailure(call: Call<StateResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//
//
//    }

    fun isValidPassword(password: String?): Boolean {
        val matcher: Matcher =
            Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})")
                .matcher(password)
        return matcher.matches()
    }

    fun phoneValidation(phone: String): Boolean {
        val pattern = Pattern.compile("(1-9)")
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }

    fun isEmpty(text: EditText?): Boolean {
        val str: CharSequence = text?.text.toString()
        return TextUtils.isEmpty(str)
    }


    private fun checkDataEntered(): Boolean {
        if (isEmpty(edtFirstname)) {
            edtFirstname.error = "Please enter first name"
            return false
        }
        if (isEmpty(edtLastname)) {
            edtLastname.error = "Please enter last name"
            return false
        }
        if (edtEmail_l.text.toString().trim().isEmpty()) {
            edtEmail_l.error = "Field can't be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail_l.text.toString().trim()).matches()) {
            edtEmail_l.error = "Please enter a valid email address"
            return false
        }
        if (isEmpty(edtAreaCode)) {
            edtAreaCode.error = "Please enter a area code is required"
            return false
        }
        if (!Patterns.PHONE.matcher(edtPhoneNumber.text.toString().trim()).matches()) {
            edtPhoneNumber.error = "Please enter a valid phone number"
            return false
        }
        if (isEmpty(edtPhoneNumber)) {
            edtPhoneNumber.error = "Please enter Phone number"
            return false
        }
        if (isEmpty(edtApartment)) {
            edtApartment.error = "Please enter apartment / unit"
            return false
        }
        if (isEmpty(edtCity)) {
            edtCity.error = "Please enter city"
            return false
        }
        if (isEmpty(zipcode)) {
            edtCity.error = "Please enter zipcode"
            return false
        }
        if (!isValidPassword(edtpassword_s.text.toString().trim())) {
            edtpassword_s.error =
                "Password must contain mix of upper and lower case letters as well as digits and one special charecter(4-20)";
            return false
        }
        if (edtpassword_s.text.toString().trim() != edtconfirm_pass.text.toString().trim()) {
            edtconfirm_pass.error = "Password and confirm password not matched"
            return false
        }
        if (!chkCondition.isChecked) {
            edtconfirm_pass.error = "Please read and accept terms and condition"
            return false
        }
        return true
    }

}