package com.example.sqllitedb

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var confirm_password:EditText
    lateinit var Register_btn: Button
    lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        name=findViewById(R.id.name)
        email=findViewById(R.id.email)
        password=findViewById(R.id.pass)
        confirm_password=findViewById(R.id.con_pass)
        Register_btn=findViewById(R.id.submit)
        dbHelper=DBHelper(applicationContext)

        Register_btn.setOnClickListener(View.OnClickListener {
            registerData()
        })

    }
    fun registerData() {
        val Name=name.text.toString().trim()
        val Email=email.text.toString().trim()
        val Pass=password.text.toString().trim()
        val Confirm_pass=confirm_password.text.toString().trim()
        val check=dbHelper.registerUserHelper(Name,Email,Pass,Confirm_pass)
        if (check){
            name.text.clear()
            email.text.clear()
            password.text.clear()
            confirm_password.text.clear()
            Toast.makeText(applicationContext,"Register Success",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext,"Register Failure",Toast.LENGTH_SHORT).show()
        }

    }

}