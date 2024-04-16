package com.speech.assistant.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.speech.assistant.base.db.dao.UserDao
import com.speech.assistant.base.db.dao.VoiceDao
import com.speech.assistant.datas.VoiceInfo

@Database(entities = [VoiceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun voiceDao(): VoiceDao
}