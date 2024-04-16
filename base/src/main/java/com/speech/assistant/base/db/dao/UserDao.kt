package com.speech.assistant.base.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.speech.assistant.datas.LoginInfo

@Dao
interface UserDao {
    @Insert
    fun insertAll(loginInfo: LoginInfo)

    @Query("SELECT * FROM login_info")
    fun getAll(): List<LoginInfo>

    @Query("DELETE FROM login_info")
    fun deleteAll()

}