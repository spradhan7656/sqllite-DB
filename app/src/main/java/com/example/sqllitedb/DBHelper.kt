package com.example.sqllitedb

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
@SuppressLint("NewApi")
class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "demo_db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val Create_Table:String?="CREATE TABLE register(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,password TEXT,gender TEXT)"
        db?.execSQL(Create_Table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS register")
        onCreate(db)
    }
    fun registerUserHelper(name:String,email:String,password:String,confirmPassword:String):Boolean{
        Log.d("name",name)
        val sqldb:SQLiteDatabase= this.writableDatabase
        val values= ContentValues()
        values.put("name",name)
        values.put("email",email)
        values.put("password",password)
        values.put("gender",confirmPassword)
        val l=sqldb.insert("register",null,values)
        sqldb.close()
        if (l>0){
            return true
        }else{
            return false
        }
    }
    fun loginUserHelper(email:String,pass:String):Boolean{
        Log.d("email",email)
        val sqldb:SQLiteDatabase= this.readableDatabase
        val query:String="SELECT * FROM register WHERE email='"+email+"' AND password='"+pass+"'"
        val result=sqldb.rawQuery(query, (null))
        if (result.moveToFirst()){
            return true
        }
        sqldb.close()
        return false
    }
    fun getLoginUser(email:String):ArrayList<UserModel>{
        var arrayList = ArrayList<UserModel>()
        val sqldb:SQLiteDatabase= this.readableDatabase
        val query:String="SELECT * FROM register WHERE email='"+email+"'"
        val result=sqldb.rawQuery(query, null)
        if (result.moveToFirst()){
            val email = result.getString(result.getColumnIndexOrThrow("email"))
            val name = result.getString(result.getColumnIndexOrThrow("name"))
            val gender = result.getString(result.getColumnIndexOrThrow("gender"))
            val addUser = UserModel(email, name, gender)
            arrayList.add(addUser)
        }
        result.close()
        return arrayList
    }
}