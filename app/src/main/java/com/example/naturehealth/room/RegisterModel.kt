package com.example.naturehealth.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register_users_table")
data class RegisterModel(
//    autoGenerate = true
    val userId: Int = 0,

    @ColumnInfo(name = "username")
    var userName: String,

    @PrimaryKey
    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,
)
