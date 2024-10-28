package com.example.sqllitedb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    lateinit var signupButton: TextView
    lateinit var login_email: EditText
    lateinit var login_password: EditText
    lateinit var login_btn: Button
    lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_email =findViewById(R.id.login_email)
        login_password =findViewById(R.id.login_pass)
        login_btn =findViewById(R.id.login_btn)
        signupButton =findViewById(R.id.signup_btn)
        dbHelper=DBHelper(applicationContext)
        login_btn.setOnClickListener(View.OnClickListener {
            login()
        })
        signupButton.setOnClickListener(View.OnClickListener {
            register()
        })
    }
    fun login(){
        val Email_login=login_email.text.toString().trim()
        val Password_login=login_password.text.toString().trim()
        val res=dbHelper.loginUserHelper(Email_login,Password_login)
        if (res){
            login_email.text.clear()
            login_password.text.clear()

            val intent= Intent(applicationContext,Profile::class.java)
            intent.putExtra("user_email",Email_login)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(applicationContext,"Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
    fun register(){
        val intent=Intent(applicationContext,Register::class.java)
        startActivity(intent) 
    }
}