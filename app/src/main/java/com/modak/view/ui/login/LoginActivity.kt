package com.modak.view.ui.login

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.modak.R
import com.modak.helper.SavedPreference
import com.modak.helper.function
import com.modak.model.RequestPojo.LoginRequest
import com.modak.model.ResponsePojo.LoginResponse
import com.modak.repositories.APIClient
import com.modak.view.ui.custom.MDToast
import com.modak.view.ui.dashboard.DashboardActivity
import com.modak.view.ui.signup.SignupActivity
import com.modak.view.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.regex.Pattern
import com.modak.helper.PrefUtils as PrefUtils1


class LoginActivity : AppCompatActivity() {
    companion object {
        fun launchActivity(
            activity: SplashActivity
        ) {
            if (activity != null) {
                val intent = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intent)
            }
        }
    }

    var loginResponse: LoginResponse = LoginResponse()
    var loginRequest: LoginRequest = LoginRequest()


    var callbackManager: CallbackManager? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    var fb_token: String? = ""
    var fullname: String? = ""
    var email: String? = null
    var socialId: String? = ""
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth
    val TAG = "LoginActivity"


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        var sph: SharedPreferences = this.getSharedPreferences(Context.MODE_PRIVATE.toString(), 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        LoginExpired()
        val hidePasswordMethod = PasswordTransformationMethod()
        eye_btn.setOnClickListener {
            edtPassword.apply {
                transformationMethod =
                    if (transformationMethod is PasswordTransformationMethod) {
                        null //shows password
                    } else {
                        hidePasswordMethod//hides password
                    }
            }
        }
        val isLogin: Boolean = false
        sph.edit().putBoolean("isLogin", isLogin).commit()
        login_facebook.setOnClickListener(View.OnClickListener {
            login_facebook.alpha = 0.5f
            fblogin()
        })
        printHashKey(this)
        val button: TextView = this.findViewById(R.id.txtSignup)
        button.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            val spannable = SpannableString("Sign up")
            spannable.setSpan(
                UnderlineSpan(),
                0,
                7,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(Color.parseColor("#F4AE14")),
                0,
                7,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            txtSignup.text = spannable
        }

        val spannable_p = SpannableString("Forgot your password?")
        spannable_p.setSpan(
            UnderlineSpan(),
            0,
            20,
            SpannableString.SPAN_EXCLUSIVE_INCLUSIVE
        )
        txt_password.text = spannable_p
        // var sph: SharedPreferences = this.getSharedPreferences(Context.MODE_PRIVATE.toString(), 0)
        login_l!!.setOnClickListener {

            if (validateEmail() && validatepassword()) {

                loginRequest.email = edtEmail.text.toString().trim()
                loginRequest.password = edtPassword.text.toString().trim()
                login_l.setTextColor(resources.getColor(R.color.yellow))
                val animation =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.blink_button)
                login_l.startAnimation(animation)
                APIClient.getService().Login(loginRequest)
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if (response.body() != null) {
                                function.showToastL(
                                    this@LoginActivity,
                                    "${response.body()!!.message.toString()} \n ${response.body()!!.first_name.toString()}",
                                    MDToast.TYPE_SUCCESS
                                )
                            }
                            if (response.body()!!.status_code?.toString() == "200") {

                                PrefUtils1.setIsUserLogin(this@LoginActivity, true)
                                PrefUtils1.setUserToken(
                                    this@LoginActivity, response.body()!!.access_token.toString()
                                )
                                com.modak.helper.PrefUtils.setFirstName(
                                    this@LoginActivity, response.body()!!.first_name.toString()
                                )
                                com.modak.helper.PrefUtils.setLastName(
                                    this@LoginActivity, response.body()!!.last_name.toString()
                                )
                                com.modak.helper.PrefUtils.setEmail(
                                    this@LoginActivity, response.body()!!.email.toString()
                                )
                                com.modak.helper.PrefUtils.setExpiries(
                                    this@LoginActivity, response.body()!!.expiries.toString()
                                )
                                com.modak.helper.PrefUtils.setLogintime(
                                    this@LoginActivity,
                                    response.body()!!.login_time.toString()
                                )

                                val intent =
                                    Intent(this@LoginActivity, DashboardActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.d("Login", t.localizedMessage.toString())
                        }
                    })


            }

        }
        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("550483134252-h8rt64904adj9te11q6a7ehj037q4k3i.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        firebaseAuth = FirebaseAuth.getInstance()

        login_google.setOnClickListener { view: View? ->
            //Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            function.showToast(this, "Logging in", MDToast.TYPE_SUCCESS)
            signInGoogle()
            onBackPressed()
        }

    }


    private fun LoginExpired() {
//        val todaysdate = Date()
//        val loginexpire= com.modak.helper.PrefUtils.getExpiries(this)
//        val format = SimpleDateFormat("yyyy-MM-dd")
//        val date1: String = format.format(todaysdate)
//        val date2:String = format.format(loginexpire)
//        val result= date1.compareTo(date2)
//        if (result == 0) {
//            println("Date1 is equal to Date2")
//        } else if (result > 0) {
//            println("Date1 is after Date2")
//            val intent=Intent(this,com.modak.view.ui.login.LoginActivity::class.java)
//            startActivity(intent)
//        } else if (result < 0) {
//            println("Date1 is before Date2")
//        }
    }

    private fun validatepassword(): Boolean {
        val passwordinput: String = edtPassword.text.toString().trim()
        return if (passwordinput.isEmpty()) {
            edtPassword.setError("Field can't be empty")
            false
        } else if (passwordinput.length > 15) {
            edtPassword.setError("Password entered too long")
            false
        } else {
            edtPassword.setError(null)
            true
        }
    }

    private fun validateUsername(): Boolean {
        val usernameInput: String = edtEmail.getText().toString().trim()

        return if (usernameInput.isEmpty()) {
            edtEmail.setError("Field can't be empty")
            false
        } else if (usernameInput.length > 15) {
            edtEmail.setError("Username too long")
            false
        } else {
            edtEmail.setError(null)
            true
        }
    }


    private fun validateEmail(): Boolean {
        val emailInput: String = edtEmail.getText().toString().trim()
        return if (emailInput.isEmpty()) {
            edtEmail.setError("Field can't be empty")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            edtEmail.setError("Please enter a valid email address")
            false
        } else {
            edtEmail.setError(null)
            true
        }
    }

    private fun fblogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"))
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    val userId = result.accessToken.userId
                    Log.d(ContentValues.TAG, "onSuccess: userId $userId")
                    val bundle = Bundle()
                    bundle.putString("fields", "id, email, first_name, last_name, gender,age_range")
                    //Graph API to access the data of user's facebook account
                    val request = GraphRequest.newMeRequest(
                        result.accessToken
                    ) { fbObject, response ->
                        Log.v("Login Success", response.toString())

                        //For safety measure enclose the request with try and catch
                        try {

                            Log.d(ContentValues.TAG, "onSuccess: fbObject $fbObject")

                            val firstName = fbObject?.getString("first_name")
                            val lastName = fbObject?.getString("last_name")
                            val gender = fbObject?.getString("gender")
                            val email = fbObject?.getString("email")

                            Log.d(ContentValues.TAG, "onSuccess: firstName $firstName")
                            Log.d(ContentValues.TAG, "onSuccess: lastName $lastName")
                            Log.d(ContentValues.TAG, "onSuccess: gender $gender")
                            Log.d(ContentValues.TAG, "onSuccess: email $email")
                            val token = AccessToken.getCurrentAccessToken()
                            fb_token = token?.token?.toString()

                        } //If no data has been retrieve throw some error
                        catch (e: JSONException) {

                        }

                    }
                    //Set the bundle's data as Graph's object data
                    var param = Bundle()
                    param.putString("fields", "id,email,first_name,last_name")
                    request.parameters = param

                    //Execute this Graph request asynchronously
                    request.executeAsync()

                }

                override fun onCancel() {
                    Log.d(ContentValues.TAG, "onCancel: called")
                }

                override fun onError(error: FacebookException) {
                    Log.d(ContentValues.TAG, "onError: called")
                }
            })

    }


    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 101)


    }


    override fun onBackPressed() {
        Firebase.auth.signOut()
        finishAffinity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Req_Code) {
            try {
                val task =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                val account =
                    task.getResult(ApiException::class.java)
                onLoggedIn(account!!)
                super.onActivityResult(requestCode, resultCode, data)
            } catch (e: ApiException) { // The ApiException status code indicates the detailed failure reason.
                Log.w(
                    "Gmail login",
                    "signInResult:failed code=" + e.statusCode
                )
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("name_l", fullname)
            startActivity(intent)
        }
    }

    private fun onLoggedIn(googleSignInAccount: GoogleSignInAccount) {
        fullname = googleSignInAccount.displayName!!
        email = googleSignInAccount.email!!
        socialId = googleSignInAccount.id!!

    }


    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            //Toast.makeText(this, "Sorry no account found!!!", Toast.LENGTH_SHORT).show()
            function.showToast(this, "Sorry no account found!!!", MDToast.TYPE_WARNING)
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                SavedPreference.setEmail(this, account.email.toString())
                SavedPreference.setUsername(this, account.displayName.toString())
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

        }
    }

    fun CheckLogin() {
        var sph: SharedPreferences = this.getSharedPreferences(Context.MODE_PRIVATE.toString(), 0)
        if (sph == null) sph = getSharedPreferences("myPreferences", MODE_PRIVATE)
        val userName: String? = sph.getString("name", "")
        val password: String? = sph.getString("password", "")
        if (userName == "mayankingle" && password == "6355254517") {
            val i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    fun onResponse(response: String) {
        var sph: SharedPreferences = this.getSharedPreferences(Context.MODE_PRIVATE.toString(), 0)
        val editor: SharedPreferences.Editor = sph.edit()
        editor.putString("login", response)
        editor.commit()
    }

    fun DoLogin(userid: String?, password: String) {
        var sph: SharedPreferences = this.getSharedPreferences(Context.MODE_PRIVATE.toString(), 0)
        try {
            if (password == "6355254517" && userid == "mayankingle") {
                if (sph == null) sph = getSharedPreferences("myPreferences", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sph.edit()
                editor.putString("name", userid)
                editor.commit()
                val i = Intent(this, DashboardActivity::class.java)
                i.putExtra("message_e", edtEmail.text.toString())
                i.putExtra("message_p", edtPassword.text.toString())
                startActivity(i)
                finish()
            } else {
                //Toast.makeText(this, "Invalid Credentails", Toast.LENGTH_LONG).show()
                function.showToast(this, "Invalid Credentails", MDToast.TYPE_ERROR)
            }
        } catch (ex: Exception) {
            // Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
            function.showToast(this, ex.message, MDToast.TYPE_ERROR)
        }
    }

    fun printHashKey(pContext: Context) {
        try {
            val info = pContext.packageManager.getPackageInfo(
                pContext.packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.i(TAG, "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "printHashKey()", e)
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "printHashKey()", e)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    // validating password with retype password
    private fun isValidPassword(pass: String?): Boolean {
        return if (pass != null && pass.length > 6) {
            true
        } else false
    }
}

