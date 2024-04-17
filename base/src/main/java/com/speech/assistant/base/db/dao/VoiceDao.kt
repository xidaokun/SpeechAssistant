package com.speech.assistant.base.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.speech.assistant.datas.VoiceInfo

@Dao
interface VoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( infos: List<VoiceInfo>)
}