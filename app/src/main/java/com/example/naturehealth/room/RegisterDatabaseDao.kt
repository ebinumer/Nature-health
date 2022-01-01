package com.example.naturehealth.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterModel)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterModel>>

    @Query("SELECT * FROM Register_users_table WHERE email LIKE :email")
    suspend fun getUsername(email: String): RegisterModel?

}
