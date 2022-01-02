package com.example.naturehealth.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.room.RoomDatabase
import com.example.naturehealth.R
import com.example.naturehealth.RepoReg
import com.example.naturehealth.home.MainActivity
import com.example.naturehealth.room.RegisterDatabase
import com.example.naturehealth.room.RegisterDatabaseDao
import com.example.naturehealth.room.RegisterModel
import com.example.naturehealth.session_manager.SessionManager
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity (): AppCompatActivity() {
    lateinit var repoReg: RepoReg
    lateinit var mSessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mSessionManager= SessionManager(this)
        repoReg = RepoReg.getInstance(RegisterDatabase.getInstance(this).registerDatabaseDao)
        btnClick()
    }

    private fun btnClick() {
        bSignUp.setOnClickListener {
            if (etUsername.text.toString().isEmpty()) {
                Toast.makeText(baseContext, "fill username", Toast.LENGTH_LONG)
                    .show()
            } else if (etEmail.text.toString().isEmpty()) {
                Toast.makeText(baseContext, "fill email", Toast.LENGTH_LONG)
                    .show()
            } else if (etPassword.text.toString().isEmpty()) {
                Toast.makeText(baseContext, "fill password", Toast.LENGTH_LONG)
                    .show()
            } else {
                GlobalScope.launch {
                    isEmailExist(etUsername.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString())
                }

            }
        }
        tvSignIn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    private fun createUser(username: String, email: String, password: String) {

        repoReg.insertUser(username,email, password)
    }

    private fun isEmailExist(username: String, email: String, password: String): Boolean {
        val isEmail=repoReg.isEmailExist(email)
        Log.e("resp", isEmail.toString())
        if(isEmail){
            runOnUiThread { Toast.makeText(this, "Email already exist", Toast.LENGTH_SHORT).show() }
        }
        else{
            createUser(username, email, password)
            runOnUiThread { Toast.makeText(baseContext, "Successfully Created An Account!", Toast.LENGTH_LONG).show()}
            mSessionManager.appOpenStatus = true

                val intent=Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        return isEmail
    }


}