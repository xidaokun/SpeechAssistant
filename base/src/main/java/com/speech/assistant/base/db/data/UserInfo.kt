package com.speech.assistant.datas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfo(@PrimaryKey val userId: String, val nickname: String, val sex: String,
                    val age: String, val tel: String, val address: String,
                    val email: String, val signature: String)
