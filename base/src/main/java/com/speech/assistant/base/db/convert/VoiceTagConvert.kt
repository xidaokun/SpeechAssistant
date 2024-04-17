package com.speech.assistant.base.db.convert

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.speech.assistant.datas.VoiceTag

class VoiceTagConvert {
    private val gson = Gson()

    @TypeConverter
    fun stringToVoiceTag(data: String?): VoiceTag {
        if (data == null) {
            return VoiceTag(listOf(), listOf())
        }
        val type = object : TypeToken<VoiceTag>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun voiceTagToString(voicetag: VoiceTag): String {
        return gson.toJson(voicetag)
    }
}