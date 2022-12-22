package com.example.foodcompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "full_name")
    val fullName: String = "$firstName $lastName",
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone_no")
    val phoneNo: Int
)
