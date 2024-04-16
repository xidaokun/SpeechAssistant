package com.speech.assistant.base.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface VoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg obj: Any)
}