package com.example.naturehealth.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.RoomDatabase
import com.example.naturehealth.R
import com.example.naturehealth.room.RegisterDatabaseDao
import com.example.naturehealth.room.RegisterModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity (private val repository: RepoAuth): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnClick()
    }

    private fun btnClick() {
        bSignUp.setOnClickListener {
//            repository.insert(RegisterModel(1,"","",""))
        }
    }

}