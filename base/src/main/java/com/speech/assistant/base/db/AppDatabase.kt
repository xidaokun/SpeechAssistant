package com.speech.assistant.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.speech.assistant.base.db.convert.VoiceTagConvert
import com.speech.assistant.base.db.dao.UserDao
import com.speech.assistant.base.db.dao.VoiceDao
import com.speech.assistant.datas.UserInfo
import com.speech.assistant.datas.VoiceInfo

@Database(entities = [VoiceInfo::class, UserInfo::class], version = 1, exportSchema = false)
@TypeConverters(VoiceTagConvert::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun voiceDao(): VoiceDao
}