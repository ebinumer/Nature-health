package com.example.naturehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.naturehealth.auth.LoginActivity
import com.example.naturehealth.home.MainActivity
import com.example.naturehealth.session_manager.SessionManager

class SplashActivity : AppCompatActivity() {
    lateinit var mSessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSessionManager = SessionManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            login()
        }, 3000)

    }

    private fun login() {
        if (mSessionManager.appOpenStatus) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}