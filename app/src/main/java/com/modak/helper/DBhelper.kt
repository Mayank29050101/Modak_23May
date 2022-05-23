package com.modak.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names along with their data types is given
        val query =
            ("CREATE TABLE $User_TABLE_NAME ($UserCOLUMN_ID INTEGER PRIMARY KEY, $UserCOLUMN_NAME TEXT, $UserCOLUMN_LASTNAME TEXT,$UserCOLUMN_EMAIL TEXT, $UserCOLUMN_PASSWORD TEXT, $UserCOLUMN_APARTMENTS TEXT, $UserCOLUMN_STREET TEXT, $UserCOULUMN_CITY TEXT, $UserCOLUMN_ZIP_CODE TEXT, $UserCOLUMN_PHONE TEXT, $UserCOLUMN_STATE TEXT)")
        // we are calling sqlite method for executing our query
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + User_TABLE_NAME)
        onCreate(db)

    }
    // This method is for adding data in our database
    fun addUser(
        name: String,
        last:String,
        email: String,
        password: String,
        apartment: String,
        street: String,
        city: String,
        zipcode: String,
        phone: String,
        state:String
    ) {
        // below we are creating a content values variable
        val values = ContentValues()
        // we are inserting our values in the form of key-value pair
        values.put(UserCOLUMN_NAME, name)
        values.put(UserCOLUMN_LASTNAME, last)
        values.put(UserCOLUMN_EMAIL, email)
        values.put(UserCOLUMN_PASSWORD, password)
        values.put(UserCOLUMN_APARTMENTS, apartment)
        values.put(UserCOLUMN_STREET, street)
        values.put(UserCOULUMN_CITY, city)
        values.put(UserCOLUMN_ZIP_CODE, zipcode)
        values.put(UserCOLUMN_PHONE, phone)
        values.put(UserCOLUMN_STATE, state)
        // here we are creating a writable variable of our database as we want to insert value in our database
        val db = this.writableDatabase
        // all values are inserted into database
        db.insert(User_TABLE_NAME, null, values)
        // at last we are closing our database
        db.close()

    }

    // below method is to get all data from our database
    fun getUser(): Cursor? {

        // here we are creating a readable variable of our database as we want to read value from it
        val db = this.readableDatabase
        // below code returns a cursor to read data from the database
        return db.rawQuery("SELECT * FROM " + User_TABLE_NAME, null)
    }


    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        return db.delete(User_TABLE_NAME, "ID = ?", arrayOf(id))
    }

        fun checkUser(email: String, password: String): Boolean {

        val columns = arrayOf(
            UserCOLUMN_EMAIL,
            UserCOLUMN_PASSWORD
        )

        val db = this.readableDatabase
        val selection = "SELECT * FROM $User_TABLE_NAME"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(
            User_TABLE_NAME,  //Table to query
            columns,  //columns to return
            arrayOf(UserCOLUMN_EMAIL,UserCOLUMN_PASSWORD).toString(),  //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,  //filter by row groups
            null
        ) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return if (cursorCount > 0) {
            true
        } else false
    }



    fun Login(email: String, password: String): Boolean {
        val query =
            "SELECT * FROM $User_TABLE_NAME  WHERE $UserCOLUMN_EMAIL = '$email' AND $UserCOLUMN_PASSWORD = '$password'"
        val db = this.readableDatabase
        val LoginQuery: Cursor = db.rawQuery(query, null)
        if (LoginQuery == null) {
            LoginQuery.close()
            return false
        }
        else return LoginQuery.count > 0
//        LoginQuery.moveToFirst()
//        val LoginResult = LoginQuery.getString(5)
//        LoginQuery.close()
//        return LoginResult
    }

    fun updateUser(
        name: String,
        last:String,
        email: String,
        password: String,
        apartment: String,
        street: String,
        city: String,
        zipcode: String,
        phone: String,
        state:String
    ) {
        // below we are creating a content values variable
        val values = ContentValues()
        // we are inserting our values in the form of key-value pair
        values.put(UserCOLUMN_NAME, name)
        values.put(UserCOLUMN_LASTNAME, last)
        values.put(UserCOLUMN_EMAIL, email)
        values.put(UserCOLUMN_PASSWORD, password)
        values.put(UserCOLUMN_APARTMENTS, apartment)
        values.put(UserCOLUMN_STREET, street)
        values.put(UserCOULUMN_CITY, city)
        values.put(UserCOLUMN_ZIP_CODE, zipcode)
        values.put(UserCOLUMN_PHONE, phone)
        values.put(UserCOLUMN_STATE, state)
        val db = this.writableDatabase
        db.update(User_TABLE_NAME, values, "id = ? ", arrayOf(UserCOLUMN_ID!!))
        db.close()
    }
    fun updatepassword(
        password: String
    ){
        val values = ContentValues()
        values.put(UserCOLUMN_PASSWORD, password)
    }
    fun updateProfile(
        name: String,
        last:String,
        apartment: String,
        street: String,
        city: String,
        zipcode: String,
        state:String
    ) {
        // below we are creating a content values variable
        val values = ContentValues()
        // we are inserting our values in the form of key-value pair
        values.put(UserCOLUMN_NAME, name)
        values.put(UserCOLUMN_LASTNAME, last)
        values.put(UserCOLUMN_APARTMENTS, apartment)
        values.put(UserCOLUMN_STREET, street)
        values.put(UserCOULUMN_CITY, city)
        values.put(UserCOLUMN_ZIP_CODE, zipcode)
        values.put(UserCOLUMN_STATE, state)
        val db = this.writableDatabase
        db.update(User_TABLE_NAME, values, "id = ? ", arrayOf(UserCOLUMN_ID!!))
        db.close()
    }

    companion object {
        // here we have defined variables for our database below is variable for database name
        // below is the variable for database version
        private val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Modak"
        const val User_TABLE_NAME = "modak_user"
        const val UserCOLUMN_ID = "id"
        const val UserCOLUMN_PASSWORD = "password"
        const val UserCOLUMN_NAME = "name"
        const val UserCOLUMN_LASTNAME = "last"
        const val UserCOLUMN_EMAIL = "email"
        const val UserCOLUMN_STREET = "street"
        const val UserCOLUMN_PHONE = "phone"
        const val UserCOLUMN_APARTMENTS = "apartments"
        const val UserCOULUMN_CITY = "city"
        const val UserCOLUMN_ZIP_CODE = "zip_code"
        const val UserCOLUMN_STATE ="state"
    }
}