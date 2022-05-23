package com.modak.view.ui.header

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.modak.R
import com.modak.helper.DBHelper
import com.modak.helper.PrefUtils
import com.modak.model.ResponsePojo.SignupDataResponse
import com.modak.view.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_editprofile.*


class EditProfileActivity : AppCompatActivity() {
    private val REQUEST_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 10
    private var signuplist: SignupDataResponse = SignupDataResponse()


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " Edit Profile"
        val button: Button = this.findViewById(R.id.save_ep)
        DynamicText()
        EditProfileApi()

        firstname.setText(PrefUtils.getFirstName(this).toString())
        lastname.setText(PrefUtils.getLastName(this).toString())
        edt_city.setText(PrefUtils.getCityName(this).toString())
        edt_zipcode.setText(PrefUtils.getZipcode(this).toString())
        edt_State.setText(PrefUtils.getState(this).toString())
        edt_add1.setText(PrefUtils.getAddress(this).toString())

        button.setOnClickListener {
            UpdateProfile()
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        edit_profile_pic.setOnClickListener(View.OnClickListener {
//            selectImage(this)
            imagedialog()
        })


//        lastname.append(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_LASTNAME)))
    }
//    fun encodeToBase64(image: Bitmap): String? {
//        val baos = ByteArrayOutputStream()
//        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
//        val b: ByteArray = baos.toByteArray()
//        val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)
//        Log.d("Image Log:", imageEncoded)
//        return imageEncoded
//    }

    private fun EditProfileApi() {
//        APIClient.getService()
//            .getSignup("Bearer " + PrefUtils.getUserToken(this), PrefUtils.getId(this)!!)
//            .enqueue(object :
//                Callback<SignupResponse> {
//                override fun onResponse(
//                    call: Call<SignupResponse>,
//                    response: Response<SignupResponse>
//                ) {
//                    firstname.setText(signuplist.fname.toString())
//                    lastname.setText(signuplist.lname.toString())
//                    edt_city.setText(signuplist.city.toString())
//                    edt_zipcode.setText(signuplist.pincode.toString())
//                    edt_State.setText(signuplist.state_name.toString())
//                }
//
//                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
    }

    private fun UpdateProfile() {
        var dbHelper = DBHelper(this, null)
        val cursor = dbHelper.getUser()
        cursor!!.moveToFirst()
        var firstname = firstname.text.toString().trim()
        var last = lastname.text.toString().trim()
        var city = edt_city.text.toString().trim()
        var zipcode: String = edt_zipcode.text.toString().trim()
        var add1 = edt_add1.text.toString().trim()
        var add2 = edt_add2.text.toString().trim()
        var state = edt_State.text.toString().trim()
        dbHelper.updateProfile(firstname, last, add1, add2, city, zipcode, state)
    }

    @SuppressLint("Range")
    private fun DynamicText() {
        var dbHelper = DBHelper(this, null)
        val cursor = dbHelper.getUser()
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    firstname.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_NAME)))
                    lastname.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_LASTNAME)))
                    edt_city.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOULUMN_CITY)))
                    edt_zipcode.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_ZIP_CODE)))
                    edt_add1.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_APARTMENTS)))
                    edt_add2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_STREET)))
                    edt_State.setText(cursor.getString(cursor.getColumnIndex(DBHelper.UserCOLUMN_STATE)))
                } while (cursor.moveToNext());
                cursor.close()
            }

        }

        dbHelper.close()

    }

    private fun imagedialog() {
        val imageDialog = Dialog(this, R.style.BottomDialog)
        val contentView: View = LayoutInflater.from(this).inflate(R.layout.image_picker, null)
        imageDialog.setContentView(contentView)
        imageDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = contentView.layoutParams
        layoutParams.width = resources.displayMetrics.widthPixels
        contentView.layoutParams = layoutParams
        imageDialog.window!!.setGravity(Gravity.BOTTOM)
        imageDialog.setCanceledOnTouchOutside(true)
        imageDialog.window!!.setWindowAnimations(R.style.BottomDialog_Animation)
        imageDialog.show()


        val picker: ConstraintLayout = imageDialog.findViewById(R.id.constraint_gallery)
        picker.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also { intent ->
                intent.type = "image/*"
                intent.resolveActivity(packageManager)?.also {
                    startActivityForResult(intent, REQUEST_PICK_IMAGE)
                }
            }
        }
        val camera: ConstraintLayout = imageDialog.findViewById(R.id.constraint_camera)
        camera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val bitmap = data?.extras?.get("data") as Bitmap
                profile_pic.setImageBitmap(bitmap)
            } else if (requestCode == REQUEST_PICK_IMAGE) {
                val uri = data?.getData()
                profile_pic.setImageURI(uri)

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("name_f", firstname.text.toString())
        startActivity(intent)
    }
}