package com.example.sqllitedb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class Profile : AppCompatActivity() {
    lateinit var eamilText: TextView
    lateinit var nameText:TextView
    lateinit var ganderText:TextView
    lateinit var log_outbtn: Button
    lateinit var edit_Profile:Button
    lateinit var db_helper:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val email_user = intent.getStringExtra("user_email")

        eamilText=findViewById(R.id.email)
        nameText=findViewById(R.id.name)
        ganderText=findViewById(R.id.gander)
        log_outbtn=findViewById(R.id.Logout_btn)
        edit_Profile=findViewById(R.id.Edit_profile)
        db_helper=DBHelper(applicationContext)
        log_outbtn.setOnClickListener(View.OnClickListener {
            log_out()
        })
        edit_Profile.setOnClickListener(View.OnClickListener {
            update_profile()
        })
        if (email_user != null) {
            setUserDate(email_user)
        }
    }

    fun setUserDate(email: String) {
        var database=db_helper.getLoginUser(email)
        for (user in database) {
            eamilText.setText(user.email)
            nameText.setText(user.username)
            ganderText.setText(user.gender)
        }

    }
    fun log_out() {
        finishAffinity()
        val intent= Intent(applicationContext,Login::class.java)
        startActivity(intent)
        finish()
    }
    fun update_profile() {

    }
}