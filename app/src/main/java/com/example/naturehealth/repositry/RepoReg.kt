package com.example.naturehealth.repositry

import androidx.lifecycle.LiveData
import com.example.naturehealth.room.RegisterDatabaseDao
import com.example.naturehealth.room.RegisterModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepoReg private constructor(private val userAccountDao: RegisterDatabaseDao) {
//    private val userAccountLiveData: LiveData<RegisterModel>? = null

     fun isValidAccount(email: String, password: String): Boolean {

            val userAccount = userAccountDao.getUser(email)
            return userAccount?.password == password

    }

    fun isEmailExist(email: String): Boolean {

        val userAccount = userAccountDao.getUser(email)
        return userAccount?.email == email

    }

     fun insertUser(username: String, email: String, password: String) {
        val account = RegisterModel(0,username,email, password)
         GlobalScope.launch {
             userAccountDao.insert(account)
         }
    }

    companion object {
        private var instance: RepoReg? = null

        fun getInstance(userAccountDao: RegisterDatabaseDao): RepoReg {
            if (instance == null) {
                instance = RepoReg(userAccountDao)
            }
            return instance!!
        }
    }
}