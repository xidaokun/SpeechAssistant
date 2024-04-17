package com.speech.assistant.base.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.speech.assistant.datas.UserInfo

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userInfo: UserInfo)

    @Query("SELECT * FROM user_info")
    fun getAll(): List<UserInfo>

    fun getUserInfoByUserId(userId: String): UserInfo {
        return getAll().find { it.userId == userId }!!
    }

    @Query("DELETE FROM user_info")
    fun deleteAll()

}