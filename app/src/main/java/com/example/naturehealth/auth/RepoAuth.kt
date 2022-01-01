package com.example.naturehealth.auth

import com.example.naturehealth.room.RegisterDatabaseDao
import com.example.naturehealth.room.RegisterModel

class RepoAuth(private val dao: RegisterDatabaseDao)  {
    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterModel) {
        return dao.insert(user)
    }

    suspend fun getUserName(email: String):RegisterModel?{
        return dao.getUsername(email)
    }
}