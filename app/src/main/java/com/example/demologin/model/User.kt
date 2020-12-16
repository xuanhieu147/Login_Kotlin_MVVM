package com.example.demologin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id_col")
    val id: Int,

    @ColumnInfo(name = "email_col")
    val email: String,

    @ColumnInfo(name = "password_col")
    val password: String
) {
    @Ignore
    constructor() : this(0, "", "")
}