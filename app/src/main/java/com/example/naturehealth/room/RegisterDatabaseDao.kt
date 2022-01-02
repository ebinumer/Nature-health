package com.example.naturehealth.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert
     fun insert(register: RegisterModel)

//    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
//    fun getAllUsers(): LiveData<List<RegisterModel>>

    @Query("SELECT * FROM Register_users_table WHERE email LIKE :email")
     fun getUser(email: String): RegisterModel?

//    @Query("select * from Register_users_table")
//    fun getDetails(): LiveData<List<RegisterModel?>?>?

}
