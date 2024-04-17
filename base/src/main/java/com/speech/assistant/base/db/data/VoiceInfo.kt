package com.speech.assistant.datas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voice_info")
data class VoiceInfo(@PrimaryKey val FriendlyName: String, val Gender: String,
                     val Language: String, val Locale: String, val ShortName: String,
                     val Status: String, val SuggestedCodec: String, val VoiceTag: VoiceTag)

@Entity
data class VoiceTag(val ContentCategories: List<String>, val VoicePersonalities: List<String>)