package com.example.naturehealth.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.naturehealth.R
import com.example.naturehealth.home.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnClick()
    }

    private fun btnClick() {
        bSignIn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        tvSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}