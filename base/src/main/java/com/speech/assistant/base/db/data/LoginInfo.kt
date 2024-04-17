package com.speech.assistant.datas

import androidx.room.Entity
import androidx.room.PrimaryKey

data class LoginInfo(
    val user_id: String,
    val access_token: String,
    val user_name: String
)