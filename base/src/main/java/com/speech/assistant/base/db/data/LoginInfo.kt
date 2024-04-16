package com.speech.assistant.datas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_info")
data class LoginInfo(
    @PrimaryKey val user_id: String,
    val access_token: String,
    val user_name: String
)