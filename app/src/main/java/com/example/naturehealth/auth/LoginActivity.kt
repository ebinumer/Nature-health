package com.example.naturehealth.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.naturehealth.R
import com.example.naturehealth.RepoReg
import com.example.naturehealth.home.MainActivity
import com.example.naturehealth.room.RegisterDatabase
import com.example.naturehealth.session_manager.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var repoReg: RepoReg
    lateinit var mSessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        repoReg = RepoReg.getInstance(RegisterDatabase.getInstance(this).registerDatabaseDao)
      mSessionManager= SessionManager(this)
        btnClick()
    }

    private fun btnClick() {
        bSignIn.setOnClickListener {

                if(txt_email.text.toString().isEmpty()){
                    Toast.makeText(this@LoginActivity, "Email or password mismatch", Toast.LENGTH_LONG).show()
                }
                else if(txt_pass.text.toString().isEmpty()){
                    Toast.makeText(this@LoginActivity, "Email or password mismatch", Toast.LENGTH_LONG).show()
                }
                else{
                    GlobalScope.launch {
            checkValidLogin(txt_email.text.toString(),txt_pass.text.toString())
//            startActivity(Intent(this,MainActivity::class.java))
        }}
        }
        tvSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }

    private fun  checkValidLogin(email: String, password: String): Boolean {
        val resp = repoReg.isValidAccount(email, password)
        Log.e("resp", resp.toString())
        if(resp){
            mSessionManager.appOpenStatus=true

                val i = Intent(this,MainActivity::class.java)
                i.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            finish()
        }
        else{
            runOnUiThread { Toast.makeText(this, "Email or password mismatch", Toast.LENGTH_SHORT).show() }

        }
        return resp
    }
}